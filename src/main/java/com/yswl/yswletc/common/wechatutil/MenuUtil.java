package com.yswl.yswletc.common.wechatutil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: jang
 * Date: 2019/10/11
 * Time: 13:47
 * Description: No Description
 */
public class MenuUtil {
    private static final String MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+TokenUtil.getAccessToken();

    /**
     * 创建微信公众号的自定义菜单
     */
    public static String createMenu(){

        //构建菜单的Json
        StringBuilder sb = new StringBuilder();
        sb.append("{");
            sb.append("\"button\":[");
            //第一个菜单
                sb.append("{");
                    sb.append("\"type\":\"click\",");
                    sb.append("\"name\":\"菜单\",");
                    sb.append("\"key\":\"mymenu\"");
                sb.append("},");

            //第二个菜单
                sb.append("{");
                    sb.append("\"name\":\"帮助\",");
                    sb.append("\"sub_button\":[");//二级菜单
                        //二级菜单
                        sb.append("{");
                        sb.append("\"type\":\"view\",");
                        sb.append("\"name\":\"关于我们\",");
                        sb.append("\"url\":\"http://www.baidu.com\"");
                        sb.append("},");

                        //二级菜单
                        sb.append("{");
                        sb.append("\"type\":\"view\",");
                        sb.append("\"name\":\"联系地址\",");
                        sb.append("\"url\":\"http://www.jd.com\"");
                        sb.append("}");
                    sb.append("]");
                sb.append("}");

            sb.append("]");
        sb.append("{");

        try {
            URL url = new URL(MENU_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();//连接服务器

            //发送请求
            OutputStream out = urlConnection.getOutputStream();
            out.write(sb.toString().getBytes("utf-8"));


            //接受响应
            InputStream in = urlConnection.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();//内存流
            int len;
            byte[] buffer = new byte[1024 * 10];
            while ((len = in.read(buffer))!= -1){
                outputStream.write(buffer,0,len);
            }
            byte[] bs = outputStream.toByteArray();
            String resultStr = new String(sb);

            //打印响应值
            System.out.println("接受到响应值："+resultStr);

            //断开连接
            urlConnection.disconnect();
            outputStream.close();

            return resultStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "网络错误，创建失败";
        }

    }



}
