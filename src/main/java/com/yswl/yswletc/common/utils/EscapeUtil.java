package com.yswl.yswletc.common.utils;

import com.google.gson.JsonObject;

/**
 * User: jang
 * Date: 2019/10/9
 * Time: 14:44
 * Description: No Description
 */
public class EscapeUtil {

    /**
     *
     * @param jsonObject json数组
     * @param string 属性名
     * @return
     */
    public static String string(JsonObject jsonObject, String string) {
        try {

            return jsonObject.get(string).getAsJsonObject().get("words").getAsString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static <T> T demo2(JsonObject jsonObject,String string,T t) {
        try {
            return (T) jsonObject.get(string).getAsJsonObject().get("words").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
