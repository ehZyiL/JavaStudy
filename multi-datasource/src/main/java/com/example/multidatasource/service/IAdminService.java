package com.example.multidatasource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.multidatasource.domain.Admin;
import com.example.multidatasource.domain.User;

import java.util.List;

/**
 * <p>
 * 用户登录表 服务类
 * </p>
 *
 * @author ehzyil
 * @since 2023-12-29
 */
public interface IAdminService extends IService<Admin> {
    List<User> getAllAdmins();
}
