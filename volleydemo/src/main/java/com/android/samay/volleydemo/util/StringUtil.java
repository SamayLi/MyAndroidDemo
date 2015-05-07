package com.android.samay.volleydemo.util;

/**
 * Created by samay on 5/7/15.
 */
public class StringUtil {
    public static boolean isEmpty(String content){
        if(content !=null && content.length()>0){
            return  false;
        }else{
            return true;
        }
    }

    public static String perUrl(String url){
        if(isEmpty(url)){
            return null;
        }
        if(url.startsWith("http://")||url.startsWith("https://")){
            return  url;
        }else{
            return "http://"+url;
        }
    }
}
