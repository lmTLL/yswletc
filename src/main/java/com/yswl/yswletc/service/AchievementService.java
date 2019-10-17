package com.yswl.yswletc.service;

import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.entity.Achievement;

public interface AchievementService {

    ResultVo achievementSubmit(Achievement achievement);

    ResultVo achievementqueryByUid(Integer uid);

    ResultVo AchievementQueryAll();
}
