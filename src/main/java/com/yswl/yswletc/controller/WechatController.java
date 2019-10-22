package com.yswl.yswletc.controller;

import com.yswl.yswletc.common.utils.SHA1;
import com.yswl.yswletc.common.wechatutil.MsgUtil;
import com.yswl.yswletc.entity.Wechat;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.TreeSet;

/**
 * User: jang
 * Date: 2019/10/10
 * Time: 14:31
 * Description: 微信接入的口controller
 */
@RestController
@CrossOrigin
@RequestMapping("/wechat")
public class WechatController {

    private static final String TOKNG = "mytoken";//微信服务号令牌
     /**
     *  微信接入入口get接口
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String wechatIndex(String signature,String timestamp,String nonce,String echostr){
        System.out.println(signature+"  "+timestamp+"  "+nonce+"  "+echostr+"  "+"接收到微信消息！！！！！！");
        //1.将signature、timestamp、nonce字典序排序
        Set<String> set = new TreeSet<>();
        set.add(TOKNG);
        set.add(timestamp);
        set.add(nonce);

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : set) {
            stringBuilder.append(s);
        }
        System.out.println(stringBuilder);
        String encode = SHA1.encode(stringBuilder.toString());
        if (encode.equals(signature)){
            return echostr;
        }
        return null;
    }

    /**
     * 接受微信公众号post请求,获取请求体中的内容
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public String wechatMSsg(@RequestBody String content){
        System.out.println(content);
        Wechat wechat = MsgUtil.parseXml(content);
        String event = MsgUtil.event(wechat);
        return event;
    }
}
