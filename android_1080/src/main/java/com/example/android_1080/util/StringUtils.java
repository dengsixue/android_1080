package com.example.android_1080.util;


import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtils {
    /**
     * 从字符串中提取数字
     *
     * @param text
     * @return
     */
    public static int keepNumbers(String text) {
        String str = text;
        try {
            return Integer.parseInt(Pattern.compile("[^0-9]").matcher(str).replaceAll("").trim());
        } catch (Exception e) {
           return 0;
        }
    }

    /****
     *
     * 从url中提取value的值
     * ***/
    public static int getId(String url){
        if (url!=null){
            String[] urls = url.split("\\?");
            if (urls.length>1){
                String[] values = urls[1].split("&");
                if (values.length>1){
                    return StringUtils.keepNumbers(values[1]);
                }
            }
        }
        return 0;
    }

    /****
     *
     * 从url中提取type的值
     * ***/
    public static String getType(String url){
        if (url!=null){
            String[] urls = url.split("\\?");
            if (urls.length>1){
                String[] values = urls[1].split("&");
                if (values.length>1){
                    return values[0].replace("type=","");
                }
            }
        }
        return "";
    }
}
