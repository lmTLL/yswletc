package com.yswl.yswletc.controller;

import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: jang
 * Date: 2019/10/23
 * Time: 9:11
 * Description: No Description
 */
@RestController
@CrossOrigin
public class BanKcardController {

    @Autowired
    private BankCardService bankCardService;

    @PostMapping("/bankCard/add")
    public ResultVo bankCardAdd(Integer uid,String name,String path){
        return bankCardService.bankCardAdd(uid,name,path);
    }
    @GetMapping("/bankCard/delete")
    public ResultVo bankCardDelete(Integer id){
        return bankCardService.bankCardDelete(id);
    }
    @GetMapping("/bankCard/queryAll")
    public ResultVo bankCardQueryAll(){
        return bankCardService.bankCardQueryAll();
    }

}
