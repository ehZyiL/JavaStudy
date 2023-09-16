package com.ehzyil.controller;

import com.ehzyil.domain.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ehyzil
 * @Description
 * @create 2023-09-2023/9/15-20:40
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    //官方提供的4个权限校验的方法
    //@PreAuthorize("hasAuthority('system:dept:list')")
    //@PreAuthorize("hasAnyAuthority('zidingyi','huanf','system:dept:list')")
    //@PreAuthorize("hasRole('system:dept:list')")
    //@PreAuthorize("hasAnyRole('zidingyi','huanf','system:dept:list')")

    //自定义权限校验的方法，huanfHasAuthority
    @PreAuthorize("@huanfEX.huanfHasAuthority('system:dept:list')")
    public String hello() {
        return "欢迎，开始你新的学习旅程吧";
    }


    @PreAuthorize("hasAuthority('test')")
    @GetMapping("/test")
    public String hello2() {
        return "hasAuthority('test')";
    }

    //基于配置的权限控制
    @RequestMapping("/configAuth")
    public ResponseResult xx(){
        return new ResponseResult(200,"访问成功");
    }
}
