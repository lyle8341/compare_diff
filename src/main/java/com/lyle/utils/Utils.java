package com.lyle.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String datePart() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return format.format(date);
    }

}
