package com.yswl.yswletc.dao;

import com.yswl.yswletc.entity.Achievement;

public interface AchievementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Achievement record);

    int insertSelective(Achievement record);

    Achievement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Achievement record);

    int updateByPrimaryKey(Achievement record);
}