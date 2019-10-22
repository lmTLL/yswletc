package com.yswl.yswletc.service;

import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.entity.Achievement;

public interface AchievementService {

    ResultVo achievementSubmit(Achievement achievement);

    ResultVo achievementqueryByUid(Integer uid);

    ResultVo AchievementQueryAll();

    ResultVo achievementQueryByCondition(String itemname, String uname, String username, String phone, String carid, Integer state, Integer day, Integer current, Integer size);

    ResultVo achievementQueryById(Integer id);

    ResultVo achievementAudit(Integer id, Integer state, String reason);

    ResultVo AchievementqueryByidAndDate(Integer id, Integer day);
}
