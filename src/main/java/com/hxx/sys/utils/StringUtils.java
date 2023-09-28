package com.hxx.sys.utils;

public class StringUtils {
    public static boolean isEmpty(String msg){
        if(!"".equals(msg)&&msg!=null){
            return false;
        }
        return true;
    }
    public static boolean isNotEmpty(String msg){
        if(!"".equals(msg)&&msg!=null){
            return true;
        }
        return false;
    }
}
