package com.yswl.yswletc.service.serviceimpl;

import com.yswl.yswletc.dao.AchievementMapper;
import com.yswl.yswletc.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AchieventmentServiceImpl implements AchievementService {

    @Autowired
    private AchievementMapper achievementMapper;

}
