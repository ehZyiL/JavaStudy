package com.example.senstive.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.senstive.domain.User;

import java.util.List;

/**
 * <p>
 * 用户登录表 服务类
 * </p>
 *
 * @author ehzyil
 * @since 2023-12-28
 */
public interface IUserService extends IService<User> {

    List<User> getAllUsers();
    User getUser(Integer id);
    void insert(User user);
}
