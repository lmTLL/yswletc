package com.yswl.yswletc.controller;

import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.entity.Orders;
import com.yswl.yswletc.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: jang
 * Date: 2019/10/23
 * Time: 17:20
 * Description: No Description
 */
@RestController
@CrossOrigin
public class OrdersController {

    @Autowired
    private OrdersService ordersService;


    //添加提款信息
    @PostMapping("/orders/add")
    public ResultVo ordersAdd(Orders orders){
        return ordersService.ordersAdd(orders);
    }
    //按用户id查询提款列表
    @GetMapping("/orders/queryByUid")
    public ResultVo ordersQueryByUid(Integer uid){
        return ordersService.ordersQueryByUid(uid);
    }
    //确认打款
    @PostMapping("/orders/affirm")
    public ResultVo ordersAffirm(Integer id,String comment,String receiptpath){
        return ordersService.ordersAffirm(id,comment,receiptpath);
    }
    //驳回打款
    @PostMapping("/orders/reject")
    public ResultVo ordersReject(Integer id,String comment){
        return ordersService.ordersReject(id,comment);
    }

    //条件查询提款列表
    @PostMapping("/bankCard/queryByterm")
    public ResultVo bankCardQueryByterm(String name,Integer statis,Integer day){
       return ordersService.bankCardQueryByterm(name,statis,day);
    }

}
