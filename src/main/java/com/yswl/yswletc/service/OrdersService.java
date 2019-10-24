package com.yswl.yswletc.service;

import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.entity.Orders;

/**
 * User: jang
 * Date: 2019/10/23
 * Time: 17:17
 * Description: No Description
 */
public interface OrdersService {

    ResultVo ordersAdd(Orders orders);

    ResultVo ordersQueryByUid(Integer uid);

    ResultVo ordersAffirm(Integer id, String comment, String receiptpath);

    ResultVo ordersReject(Integer id, String comment);

    ResultVo bankCardQueryByterm(String name, Integer statis, Integer day);
}
