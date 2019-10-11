package com.yswl.yswletc.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yswl.yswletc.entity.User;

public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByName(String name);
}