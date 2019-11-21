package com.yswl.yswletc.common.utils;

import com.yswl.yswletc.entity.SystemWechat;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: jang
 * Date: 2019/11/7
 * Time: 16:17
 * Description: No Description
 */
public class WxTemplateUtil {
    public static String formatTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
    public static WxMpInMemoryConfigStorage templateDeploy(){
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(SystemWechat.APP_ID);//配置APPID
        wxStorage.setSecret(SystemWechat.APP_SECRET);//配置app秘钥

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        return wxStorage;

    }
}
