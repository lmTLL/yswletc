package com.yswl.yswletc.common.wechatutil;

import com.yswl.yswletc.entity.Wechat;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;

/**
 * User: jang
 * Date: 2019/10/11
 * Time: 12:27
 * Description: No Description
 */
public class MsgUtil {

    /**
     * 解析用户消息
     */
    public static Wechat parseXml(String xml){
        System.out.println(xml);
        Class<Wechat> wechatClass = Wechat.class;
        Wechat wechat = new Wechat();

        Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
            Element rootElement = document.getRootElement();//获取跟标签

            Field[] fields = wechatClass.getDeclaredFields();//获取所有声明字段

            for (Field field : fields) {
                field.setAccessible(true);//授权：获取私有的内容

                Element element = rootElement.element(field.getName());
                if(element != null){//xml标签里不为空，说明有此属性！
                    String value = element.getTextTrim();
                    field.set(wechat,value);//赋值给这个对象
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wechat;

    }

    /**
     * 处理事件
     */
    public static String event(Wechat wechat){
        if (wechat.getMsgType().equals("event")
                && wechat.getEvent().equals("subscribe")
                || wechat.getEventKey().equals("mymenu")){//如果关注的是微信公众号,则返回的是图文菜单
            String s = AnswerUtil.PictureText(wechat);
            return s;
        }
        return "success";
    }

}























































































/**
 * 文本回复
 */
//    String rcontent = "****";
//    //准备回复给用户消息
//    StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("<xml>");
//            stringBuilder.append("<ToUserName>").append(wechat.getToUserName()).append("</ToUserName>");
//            stringBuilder.append("<FromUserName>").append(wechat.getFromUserName()).append("</FromUserName>");
//            stringBuilder.append("<CreateTime>").append(System.currentTimeMillis()).append("</CreateTime>");
//            stringBuilder.append("<MsgType>text</MsgType>");
//            stringBuilder.append("<Content>"+rcontent+"</Content>");
//            stringBuilder.append("</xml>");
//            return stringBuilder.toString();
