package com.yswl.yswletc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: jang
 * Date: 2019/10/11
 * Time: 12:18
 * Description: 微信处理表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wechat {

    /**
     * 文本消息
     */
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private String Content;
    private String MsgID;

    /**
     * 图片消息
     */
    private String PicUrl;
    private String MediaId;

    /**
     * 关注事件
     */
    private String Event;
    private String EventKey;
}
