package com.example.senstive.controller;


import com.example.senstive.domain.User;
import com.example.senstive.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户登录表 前端控制器
 * </p>
 *
 * @author ehzyil
 * @since 2024-01-02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    public Object query(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }
    @GetMapping()
    public Object getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping()
    public void insert(@RequestBody User user) {
        userService.insert(user);
    }


}
