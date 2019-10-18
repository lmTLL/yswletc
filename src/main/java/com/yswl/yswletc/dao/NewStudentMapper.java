package com.yswl.yswletc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yswl.yswletc.entity.NewStudent;
import com.yswl.yswletc.entity.Student;

import java.util.List;

/**
 * User: jang
 * Date: 2019/10/17
 * Time: 13:25
 * Description: No Description
 */
public interface NewStudentMapper extends BaseMapper<NewStudent> {

    Integer deleteAll();//删除所有数据

    Integer insertAll(List<Student> lists); //批量插入
}
