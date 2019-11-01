package com.yswl.yswletc.common.wechatutil;

import com.yswl.yswletc.entity.SystemWechat;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: jang
 * Date: 2019/10/11
 * Time: 15:54
 * Description: No Description
 */
public class TokenUtil {

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ SystemWechat.APP_ID+"&secret="+SystemWechat.APP_SECRET;


    public static void main(String[] args) {

    }
    /**
     * 获取微信公众号全局唯一调用凭证
     */
    public static String getAccessToken(){
        String result = getUrl(ACCESS_TOKEN_URL);
        JSONObject jsonObject = new JSONObject(result);
        String access_token = jsonObject.getString("access_token");
        return access_token;
    }
    public static String getUrl(String urlstr){
        HttpURLConnection conn = null;
        ByteArrayOutputStream outputStream = null;
        try {
            URL url = new URL(urlstr);
            conn = (HttpURLConnection)url.openConnection();
            conn.connect();

            InputStream inputStream = conn.getInputStream();
            outputStream = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[1204*10];
            while ((len = inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,len);
            }
            return new String(outputStream.toByteArray());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null){
                conn.disconnect();
            }
        }
        return null;
    }
}
