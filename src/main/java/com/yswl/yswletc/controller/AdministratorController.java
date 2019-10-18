package com.yswl.yswletc.controller;

import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: jang
 * Date: 2019/10/17
 * Time: 9:23
 * Description: No Description
 */
@RestController
@CrossOrigin
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @PostMapping("/administrator/login")
    public ResultVo administratorLogin(String username,String password){
        return administratorService.administratorLogin(username,password);
    }
}
