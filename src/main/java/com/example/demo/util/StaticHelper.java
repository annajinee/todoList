package com.example.demo.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by annakim on 8/1/17.
 */
@Component
public class StaticHelper {
    public static String getJsonValue(JSONArray arr, int idx, String key) {
        try {
            return ((JSONObject) arr.get(idx)).get(key).toString();
        } catch (Exception ex) {
            return "";
        }
    }

    public static String getJsonValue(JSONObject reqObj, String key, String defaultValue) {

        try {
            if(reqObj.get(key).toString()!=null && !reqObj.get(key).toString().equals("")){
                return reqObj.get(key).toString();
            }else{
                return defaultValue;
            }
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public static JSONArray getJsonValue(JSONObject reqObj, String key, JSONArray defaultValue) {
        try {
            return (JSONArray) reqObj.get(key);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    // 특수문자 제거 및 숫자 여부 체크
    public static boolean checkNumber(String str) {
        if(str.contains("-")){
            str = str.replaceAll("-", "");
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static String getNowTimeStrByFormat(String formmat) {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat(formmat);
        String regTim = dayTime.format(new Date(time));
        return regTim;
    }
}
