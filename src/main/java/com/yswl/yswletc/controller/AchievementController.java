package com.yswl.yswletc.controller;

import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.entity.Achievement;
import com.yswl.yswletc.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    @PostMapping("/Achievement/submit")
    public ResultVo achievementSubmit(Achievement achievement) {
        return achievementService.achievementSubmit(achievement);
    }
    @GetMapping("/Achievement/queryByUid")
    public ResultVo achievementqueryByUid(Integer uid) {
        return achievementService.achievementqueryByUid(uid);
    }
    @GetMapping("/Achievement/queryAll")
    public ResultVo AchievementQueryAll(){
        return achievementService.AchievementQueryAll();
    }
}
