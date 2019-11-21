package com.yswl.yswletc.controller;

import com.yswl.yswletc.common.vo.ResultVo;
import com.yswl.yswletc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: jang
 * Date: 2019/10/16
 * Time: 15:36
 * Description: No Description
 */
@RestController
@CrossOrigin
public class PushController {

    @Autowired
    private MessageService messageService;

    /**
     * 模板消息群发
     */
    @RequestMapping("/push/massTexting")
    public ResultVo PushMassTexting(String content) {
        return messageService.pushMassTexting(content);
    }
    /**
     * 业绩审核通过
     */
    @RequestMapping("/push/achievementPass")
    public ResultVo achievementPass(Integer id,String comment){
        return messageService.achievementPass(id,comment);
    }
    /**
     * 业绩审核未通过
     */
    @RequestMapping("/push/achievementject")
    public ResultVo pushachievementjects(Integer id,String comment){
        return messageService.achievementject(id,comment);
    }

    /**
     * 提现通过
     */
    @RequestMapping("/push/ordersPass")
    public ResultVo pushOrdersPass(Integer id){
        return messageService.pushOrdersPass(id);
    }
    /**
     * 提现驳回616231321
     */
    @RequestMapping("/push/ordersReject")
    public ResultVo pushOrdersReject(Integer id,String comment){
        return messageService.pushOrdersReject(id,comment);
    }
}

