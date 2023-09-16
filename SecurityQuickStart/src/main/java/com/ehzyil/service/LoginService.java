package com.ehzyil.service;

import com.ehzyil.domain.ResponseResult;
import com.ehzyil.domain.User;

import org.springframework.stereotype.Service;


@Service
public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}