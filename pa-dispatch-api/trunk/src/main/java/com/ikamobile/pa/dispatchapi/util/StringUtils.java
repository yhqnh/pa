package com.ikamobile.pa.dispatchapi.util;

/**
 * Created by guest on 16/7/14.
 */
public class StringUtils {
    public static boolean isNullOrEmpty(String s){
        return s == null || s.trim().length() == 0;

    }
    public static boolean isNotBlank(String s){
        return !isNullOrEmpty(s);
    }
}
