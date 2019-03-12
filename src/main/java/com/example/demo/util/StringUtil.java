package com.example.demo.util;

public class StringUtil {
    public static String strToNullAndEnptyStr(String str) {
        if (str == null || str.equals(""))
            return  "";
        else
            return str;
    }
    public static int parseInt(String str) {
        try {
             return Integer.parseInt(str);
        } catch (Exception ex) {
            return -1;
        }
    }
    public static long parseLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception ex) {
            return -1L;
        }
    }
}
