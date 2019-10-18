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
    public ResultVo achievementQueryAll(){
        return achievementService.AchievementQueryAll();
    }
    @GetMapping("/Achievement/queryByCondition")
    public ResultVo achievementQueryByCondition(String itemname,String uname,String username,String phone,String carid,Integer state,Integer day,Integer current,Integer size){
        return achievementService.achievementQueryByCondition(itemname,uname,username,phone,carid,state,day,current,size);
    }

}
