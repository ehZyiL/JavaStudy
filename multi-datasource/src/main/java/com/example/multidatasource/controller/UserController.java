package com.example.multidatasource.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.multidatasource.dal.DsAno;
import com.example.multidatasource.dal.DsSelectExecutor;
import com.example.multidatasource.dal.MasterSlaveDsEnum;
import com.example.multidatasource.domain.User;
import com.example.multidatasource.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户登录表 前端控制器
 * </p>
 *
 * @author ehzyil
 * @since 2023-12-28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @DsAno(MasterSlaveDsEnum.STORY)
    @GetMapping("/story")
    public Object test(){
        return userService.getAllUsers();
    }


    @DsAno(MasterSlaveDsEnum.SHOP)
    @GetMapping("/shop")
    public Object test1(){
        return userService.getAllUsers();
    }


    @GetMapping("/shop2")
    public Object test2(){
        List<User> submit = DsSelectExecutor.submit(MasterSlaveDsEnum.BLOG, () -> userService.getAllUsers());
        return  submit;
    }

}
