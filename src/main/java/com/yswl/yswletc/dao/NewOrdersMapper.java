package com.yswl.yswletc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yswl.yswletc.entity.Achievement;
import com.yswl.yswletc.entity.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: jang
 * Date: 2019/10/23
 * Time: 17:15
 * Description: No Description
 */
public interface NewOrdersMapper extends BaseMapper<Orders> {

    Integer deleteAll();//删除所有数据

    Integer insertAll(List<Orders> list); //批量插入
}
