package com.example.multidatasource.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.multidatasource.dal.DsAno;
import com.example.multidatasource.dal.MasterSlaveDsEnum;
import com.example.multidatasource.domain.Admin;
import com.example.multidatasource.domain.User;
import com.example.multidatasource.mapper.AdminMapper;
import com.example.multidatasource.service.IAdminService;
import com.example.multidatasource.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户登录表 服务实现类
 * </p>
 *
 * @author ehzyil
 * @since 2023-12-29
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private IUserService userService;
    @Override

    public List<User> getAllAdmins() {

        List<User> allUsers =userService.getAllUsers();
        List<User> allAdmins = getBaseMapper().getAllAdmins();
        allAdmins.addAll(allUsers);
        return allAdmins;
    }
}
