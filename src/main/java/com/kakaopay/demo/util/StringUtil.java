package com.kakaopay.demo.util;

import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class StringUtil {

    public static String getCurrentDateTime() {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dayTime.format(new Date(time));
    }
}
