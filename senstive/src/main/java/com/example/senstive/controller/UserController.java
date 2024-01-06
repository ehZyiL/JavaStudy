package com.example.senstive.controller;


import com.example.senstive.domain.User;
import com.example.senstive.senstive.SensitiveService;
import com.example.senstive.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @Autowired
    private SensitiveService sensitiveService;

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

    /**
     * 敏感词校验
     *
     * @param txt
     * @return
     */
    @GetMapping(path = "sensitive/check")
    public List<String> check(String txt) {
        return sensitiveService.findAll(txt);
    }

    @GetMapping(path = "sensitive/contains")
    public List<String> contains(String txt) {
        return sensitiveService.contains(txt);
    }

    @GetMapping(path = "sensitive/replace")
    public String replace(String txt) {
        return sensitiveService.replace(txt);
    }


}
