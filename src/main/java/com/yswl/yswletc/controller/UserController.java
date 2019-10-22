package com.yswl.yswletc.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.yswl.yswletc.common.redis.RedisOperator;
import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.entity.User;
import com.yswl.yswletc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
        System.out.println(user.getName());
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
    @PostMapping("/user/updataCommissionById.do")
    public ResultVo userUpdataCommissionById(User user){
        return userService.userUpdataCommissionById(user);
    }
    @GetMapping("/user/myteamById.do")
    public ResultVo userMyteamById(Integer id){
        return userService.userMyteamById(id);
    }
    //=================================后台管理接口
    @GetMapping("/user/queryAllByPaging.do")
    public ResultVo userQueryAllByPaging(Integer current, Integer size,String name,String phone){
       return userService.userQueryAllByPaging(current,size,name,phone);
    }
    @GetMapping("/user/queryById.do")
    public ResultVo userQueryById(Integer id){
        return userService.userQueryById(id);
    }
}