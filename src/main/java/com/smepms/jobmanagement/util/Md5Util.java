package com.smepms.jobmanagement.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/1/22.
 */
public class Md5Util {
//    创建十六进制转换字符串数组
    private static final String[] KEYS={"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
//    单个byte转字符的方法
    private static String byteToStrings(byte b){
        int k = b ;
        if(k<0){
            k+=256;
        }
        String s1 = KEYS[k/16];
        String s2 = KEYS[k%16];
        return s1+s2;
    }
//    byte数组转字符串的方法
    private static String byteArrayToString(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b:bytes){
            stringBuilder.append(byteToStrings(b));
        }
        return stringBuilder.toString();
    }
//    传入密码，返回指定格式字符串
    public static String md5(String password){
        try {
            //通过java.security包中获取md5加密类对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //通过获取加密后的byte数组
            byte[] bytes = messageDigest.digest(password.getBytes());
            //返回加密后的密文字符串
            return byteArrayToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
