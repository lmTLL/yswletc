package com.yswl.yswletc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yswl.yswletc.entity.Student;

import java.util.List;

/**
 * User: jang
 * Date: 2019/10/17
 * Time: 13:25
 * Description: No Description
 */
public interface StudentMapper extends BaseMapper<Student> {

    List<Student> queryStudentByDay(Integer day);

    Integer insertList(List<Student> lists);
}
