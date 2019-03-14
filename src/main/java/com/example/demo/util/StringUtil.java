package com.example.demo.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class StringUtil {

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

    public static String IsNullOrEmpty(String str) {
        if (str == null || str.equals(""))
            return  "";
        else
            return str;
    }


    public static String getCurrentDateTime() {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String regTim = dayTime.format(new Date(time));
        return regTim;
    }
}
