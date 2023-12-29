package com.example.multidatasource.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.multidatasource.domain.User;
import org.apache.ibatis.annotations.Select;

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
}
