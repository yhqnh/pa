package com.ikamobile.pa.common.utils;

import java.util.List;

/**
 * Created by guest on 16/7/13.
 */
public class ListUtils {
    public static boolean isNullOrEmpty(List list){
        return list == null || list.size() == 0;
    }
}
