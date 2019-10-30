package com.yswl.yswletc.controller;

import com.yswl.yswletc.common.utils.ResultUtil;
import com.yswl.yswletc.common.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: jang
 * Date: 2019/10/28
 * Time: 9:01
 * Description: No Description
 */
@RestController
@CrossOrigin
public class InformController {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @GetMapping("/orders/inform")
    public ResultVo ordersInform(){
        try {
            String s = redisTemplate.opsForValue().get("orders");
            if (s == null){
                return ResultUtil.exec(false,"ERROR","暂无消息");
            }else {
                return ResultUtil.exec(true,"OK",s);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","网络错误");
        }finally {
            redisTemplate.delete("orders");
        }
    }

    @GetMapping("/achievement/inform")
    public ResultVo achievementInform(){
        try {
            String s = redisTemplate.opsForValue().get("achievement");
            if (s == null){
                return ResultUtil.exec(false,"ERROR","暂无消息");
            }else {
                return ResultUtil.exec(true,"OK",s);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.exec(false,"ERROR","网络错误");
        }finally {
            redisTemplate.delete("achievement");
        }
    }
}
