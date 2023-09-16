package com.ehzyil.controller;

import com.ehzyil.domain.ResponseResult;
import com.ehzyil.domain.User;
import com.ehzyil.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    //LoginService是我们在service目录写好的接口
    private LoginService loginService;

    @PostMapping("/user/login")
    //ResponseResult和user是我们在domain目录写好的类
    public ResponseResult xxlogin(@RequestBody User user){
        //登录
        return loginService.login(user);
    }
    //-----------------------------------退出登录--------------------------------

    @RequestMapping("/user/logout")
    //ResponseResult是我们在domain目录写好的实体类
    public ResponseResult logout(){
        return loginService.logout();
    }

}