package com.smepms.common.util.formatutil;

/**
 * Created by Administrator on 2018/1/25.
 */
public class StringFormatUtil {
    //模糊查询时防止用户输入'%'和'<>'
    public static String userInputSearchFormat(String temp){
        if (temp == null || temp == "") {
            return "";
        }
        StringBuilder b = new StringBuilder();
        b.append(temp.trim());
        StringBuilder tempBuilder = new StringBuilder();
        tempBuilder.append(temp.trim());
        for (int i = 0, j = 0; i < b.length(); i++) {
            char x = b.charAt(i);
            if (x == '%' || x == '_') {
                tempBuilder.insert(i + j, '\\');
                j++;
            }
        }
        return tempBuilder.toString();
    }
    public static String userIntroductionFormat(String introduction){
        StringBuilder b = new StringBuilder();
        b.append(introduction.trim());
        StringBuffer searchTemp = new StringBuffer(introduction);
        for (int i = 0, j = 0; i < searchTemp.length(); i++) {
            char x = searchTemp.charAt(i);
            if (x == '<') {
                //替换此字符为&lt;
                //一个变成三个
                b.replace(i+j,i+j+1,"&lt");
                j=j+2;
            }
            if (x == '>') {
                //替换此字符为&lt;
                //一个变成三个
                b.replace(i+j,i+j+1,"&gt");
                j=j+2;
            }
        }
        return b.toString();
    }
}
