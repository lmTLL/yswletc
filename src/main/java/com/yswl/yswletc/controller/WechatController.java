package com.yswl.yswletc.controller;

import com.yswl.yswletc.common.utils.SHA1;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * User: jang
 * Date: 2019/10/10
 * Time: 14:31
 * Description: 微信接入的口controller
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {
    private static final String TOKNG = "*****";//微信服务号设置的令牌
    /**
     *  微信接入入口
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
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            stringBuilder.append(next);
        }
        String encode = SHA1.encode(stringBuilder.toString());
        if (!encode.equals(stringBuilder)){
            return null;
        }
        return echostr;
    }

    /**
     * 接受微信公众号post请求
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public String wechatMSsg(@RequestBody String content){
        System.out.println(content);
        return null;
    }
}
