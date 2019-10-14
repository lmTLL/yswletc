package com.yswl.yswletc.controller;

import com.yswl.yswletc.common.redis.RedisOperator;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.entity.User;
import com.yswl.yswletc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private RedisOperator redis;

    @Autowired
    private UserService userService;

    @PostMapping("/user/login.do")
    public ResultVo userLogin(User user){
        return userService.userLogin(user);
    }
    @PostMapping("/user/register.do")
    public ResultVo userRegister(User user){
        return userService.userRegister(user);
    }


    @PostMapping("/user/alterPassword.do")
    public ResultVo userAlterPassword(String name,String phone,String password,String newPassword){
        return userService.userAlterPassword(name,phone,password,newPassword);
    }

}