package com.example.senstive.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.senstive.domain.User;
import com.example.senstive.mapper.UserMapper;
import com.example.senstive.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户登录表 服务实现类
 * </p>
 *
 * @author ehzyil
 * @since 2024-01-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return getBaseMapper().getAllUsers();
    }

    @Override
    public User getUser(Integer id) {
        return getBaseMapper().getUser(id);
    }

    @Override
    public void insert(User user) {
        userMapper.insertU(user);
    }

}
