package com.yswl.yswletc.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yswl.yswletc.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User queryByUid(Integer uid);
}