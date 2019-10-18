package com.yswl.yswletc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yswl.yswletc.entity.Achievement;
import com.yswl.yswletc.entity.NewAchievement;

import java.util.List;

/**
 * User: jang
 * Date: 2019/10/18
 * Time: 11:33
 * Description: No Description
 */
public interface NewAchievementMapper extends BaseMapper<NewAchievement> {

    Integer deleteAll();//删除所有数据

    Integer insertAll(List<Achievement> list); //批量插入

}
