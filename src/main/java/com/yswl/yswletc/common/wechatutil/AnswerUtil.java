package com.yswl.yswletc.common.wechatutil;

import com.yswl.yswletc.entity.Wechat;

/**
 * User: jang
 * Date: 2019/10/21
 * Time: 14:15
 * Description: No Description
 */
public class AnswerUtil {
    /**
     *解析为图文消息
     */
    public static String PictureText(Wechat wechat){
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
        sb.append("<PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz_jpg/b6ACKk4gpIxMVaNsYZibicacvNHHR8MS5WJzicgWCWWARzQh1RJ44jicEUWjK1pfVYcKtZUNwdgPVLB6Zj1UBTo06g/0]]></PicUrl>");//图片路径
        sb.append("<Url><![CDATA[http://www.baidu.com]]></Url>");//点击图片跳转的路径
        sb.append("</item>");

        sb.append("<item>");
        sb.append("<Title><![CDATA[去京东]]></Title>");//标题
        sb.append("<Description><![京东购物商城，购你所想]]></Description>");//说明
        sb.append("<PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz_jpg/b6ACKk4gpIxMVaNsYZibicacvNHHR8MS5WeEkPDNclrbCoLqEXkf3wxIozQX3rOS2uu6icMb61xep1bj4TI3PICFA/0]]></PicUrl>");//图片路径
        sb.append("<Url><![CDATA[http://www.jd.com]]></Url>");//点击图片跳转的路径
        sb.append("</item>");


        sb.append("<item>");
        sb.append("<Title><![CDATA[去淘宝]]></Title>");//标题
        sb.append("<Description><![淘宝商城，购你所想]]></Description>");//说明
        sb.append("<PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz_jpg/b6ACKk4gpIxMVaNsYZibicacvNHHR8MS5W2701U1MMqPibUI3YgS33H7BLwz2m8JhtY3FicasBfkJbqdvKKky4egmg/0]]></PicUrl>");//图片路径
        sb.append("<Url><![CDATA["+"http://www.taobao.com"+"]]></Url>");//点击图片跳转的路径
        sb.append("</item>");

        sb.append("<item>");
        sb.append("<Title><![CDATA["+"我的位置"+"]]></Title>");//标题
        sb.append("<Description><!["+"展示我在地图上的位置"+"]></Description>");//说明
        sb.append("<PicUrl><![CDATA["+"http://mmbiz.qpic.cn/mmbiz_jpg/b6ACKk4gpIxMVaNsYZibicacvNHHR8MS5WJzicgWCWWARzQh1RJ44jicEUWjK1pfVYcKtZUNwdgPVLB6Zj1UBTo06g/0"+"]]></PicUrl>");//图片路径
        sb.append("<Url><![CDATA["+"http://www.taobao.com"+"]]></Url>");//点击图片跳转的路径
        sb.append("</item>");
        sb.append("</Articles>");
        sb.append("</xml>");
        return sb.toString();
    }
}
