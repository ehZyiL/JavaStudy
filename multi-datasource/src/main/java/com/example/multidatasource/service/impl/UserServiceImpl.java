package com.example.multidatasource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.multidatasource.dal.DsAno;
import com.example.multidatasource.dal.MasterSlaveDsEnum;
import com.example.multidatasource.domain.User;
import com.example.multidatasource.mapper.UserMapper;
import com.example.multidatasource.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户登录表 服务实现类
 * </p>
 *
 * @author ehzyil
 * @since 2023-12-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    @DsAno(MasterSlaveDsEnum.BLOG)
    public List<User> getAllUsers() {
        return getBaseMapper().getAllUsers();
    }


}
