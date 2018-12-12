package com.bill.materiel.utils;

/**
 * Created by Bill
 */
public class Utils {
    public static Boolean isCardId(String str){
        return str.matches("(\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");
    }

    public static Boolean isNumber(String str){
        return str.matches("[0-9]+");
    }

    /**
     * 获取文件后缀名(包含.)
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static String buildText(String text){
        StringBuffer buffer = new StringBuffer();
        String array[] = text.split(";");
        for (String s : array) {
            buffer.append(s.trim()).append("\n");
        }
        return buffer.toString();
    }
}
