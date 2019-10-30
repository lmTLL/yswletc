package com.yswl.yswletc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yswl.yswletc.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: jang
 * Date: 2019/10/23
 * Time: 17:15
 * Description: No Description
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    List<Orders> ordersQueryByterm(@Param("name")String name, @Param("status") Integer status,@Param("day") Integer day);
}
