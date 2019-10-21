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
                if(element != null){
                    String value = element.getTextTrim();
                    field.set(wechat,value);
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
        if (wechat.getEvent().equals("event") && wechat.getEvent().equals("subscribe")
        || wechat.getEventKey().equals("mymenu")){//如果关注的是微信公众号,则返回的是图文菜单
            StringBuilder sb = new StringBuilder();
            sb.append("<xml>");
                sb.append("<ToUserName>").append(wechat.getFromUserName()).append("</ToUserName>");
                sb.append("<FromUserName>").append(wechat.getToUserName()).append("</FromUserName>");
                sb.append("<CreateTime>").append(System.currentTimeMillis()).append("</CreateTime>");
                sb.append("<MsgType><![CDATA[news]]></MsgType>");
                //设置图文消息的数量，最多设置为8个，这个是微信公众号的限制
                sb.append("<ArticleCount>4</ArticleCount>");
                    sb.append("<Articles>");
                        //每个item标签戴白一个图文消息，第一个item是大图显示，其他的item都市小图+标题形式
                        sb.append("<item>");
                            sb.append("<Title><![CDATA[欢迎关注改微信公众号]]></Title>");//标题
                            sb.append("<PicUrl><![CDATA[http://pic.netbian.com/uploads/allimg/191005/165356-15702656367a72.jpg]]></PicUrl>");//图片路径
                            sb.append("<Url><![CDATA[http://www.baidu.com]]></Url>");//点击图片跳转的路径
                        sb.append("</item>");

                        sb.append("<item>");
                            sb.append("<Title><![CDATA[去京东]]></Title>");//标题
                            sb.append("<Description><![京东购物商城，购你所想]]></Description>");//说明
                            sb.append("<PicUrl><![CDATA[http://pic.netbian.com/uploads/allimg/191005/165356-15702656367a72.jpg]]></PicUrl>");//图片路径
                            sb.append("<Url><![CDATA[http://www.jd.com]]></Url>");//点击图片跳转的路径
                        sb.append("</item>");


                        sb.append("<item>");
                            sb.append("<Title><![CDATA[去淘宝]]></Title>");//标题
                            sb.append("<Description><![淘宝商城，购你所想]]></Description>");//说明
                            sb.append("<PicUrl><![CDATA[http://pic.netbian.com/uploads/allimg/191005/165356-15702656367a72.jpg]]></PicUrl>");//图片路径
                            sb.append("<Url><![CDATA[http://www.taobao.com]]></Url>");//点击图片跳转的路径
                        sb.append("</item>");

                        sb.append("<item>");
                            sb.append("<Title><![CDATA[我的位置]]></Title>");//标题
                            sb.append("<Description><![展示我在地图上的位置]]></Description>");//说明
                            sb.append("<PicUrl><![CDATA[http://pic.netbian.com/uploads/allimg/191005/165356-15702656367a72.jpg]]></PicUrl>");//图片路径
                            sb.append("<Url><![CDATA[]]></Url>");//点击图片跳转的路径
                        sb.append("</item>");
                    sb.append("</Articles>");
            sb.append("</xml>");
            return sb.toString();
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
