package com.yswl.yswletc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yswl.yswletc.entity.Achievement;

import java.util.List;

public interface AchievementMapper extends BaseMapper<Achievement> {

    List<Achievement> selectAllAchievementByTime(Integer uid);//查看当前用户今天业绩接口



}