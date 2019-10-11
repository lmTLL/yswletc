package com.yswl.yswletc.controller;

import com.yswl.yswletc.common.redis.RedisOperator;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.entity.User;
import com.yswl.yswletc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private RedisOperator redis;

    @Autowired
    private UserService userService;

    @PostMapping("/userLogin.do")
    public ResultVo userLogin(User user){
        System.out.println("登陆UserController"+redis.get("loginName"));

        return userService.userLogin(user);
    }




}