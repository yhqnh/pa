package com.ikamobile.pa.common.utils;


import java.util.UUID;

/**
 * Created by 12613 on 2016/4/24.
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtils.getUUID());
    }
}
