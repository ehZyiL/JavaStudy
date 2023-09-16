package com.ehzyil.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ehzyil.domain.LoginUser;
import com.ehzyil.domain.User;
import com.ehzyil.mapper.MenuMapper;
import com.ehzyil.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    //MenuMapper是我们在mapper目录写好的接口，作用是查询来自数据库的权限信息
    private MenuMapper menuMapper;

    @Override
    //UserDetails是Security官方提供的接口
    public UserDetails loadUserByUsername(String xxusername) throws UsernameNotFoundException {

        //查询用户信息。我们写的userMapper接口里面是空的，所以调用的是mybatis-plus提供的方法
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //eq方法表示等值匹配，第一个参数是数据库的用户名，第二个参数是我们传进来的用户名，这两个参数进行比较是否相等
        queryWrapper.eq(User::getUserName,xxusername);
        User user = userMapper.selectOne(queryWrapper);
        //如果用户传进来的用户名，但是数据库没有这个用户名，就会导致我们是查不到的情况，那么就进行下面的判断。避免程序安全问题
        if(Objects.isNull(user)){//判断user对象是否为空。当在数据库没有查到数据时，user就会为空，也就会进入这个判断
            throw new RuntimeException("用户名或者密码错误");
        }
        //--------------------------------查询用户权限信息---------------------------------

        //由于我们自定义了3个权限，所以用List集合存储。注意权限实际就是'有特殊含义的字符串'，所以下面的三个字符串就是自定义的
        //下面那行就是我们的权限集合，等下还要在LoginUser类做权限集合的转换
//        List<String> list = new ArrayList<>(Arrays.asList("test","adminAuth","huanfAuth"));


        //-------------------------------查询来自数据库的权限信息--------------------------------

        List<String> list = menuMapper.selectPermsByUserId(user.getId());

        //------------------------------------------------------------------------------

        //把查询到的user结果，封装成UserDetails类型，然后返回。
        //但是由于UserDetails是个接口，所以我们先需要在domino目录新建LoginUser类，作为UserDetails的实现类，再写下面那行
        return new LoginUser(user,list); //这里传了第二个参数，表示的是权限信息
    }

}