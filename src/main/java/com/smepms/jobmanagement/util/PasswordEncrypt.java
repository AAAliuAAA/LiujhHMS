package com.smepms.jobmanagement.util;

/**
 * Created by Administrator on 2018/1/22.
 */
public class PasswordEncrypt {
    /***
     * 加盐的字符串，讲道理这个字符串应该是在员工表格中的一个字段，由UUID生成
     * 但是现在看来加一个字段的话为时已晚，而且内部系统对安全性要求也不高
     * 就瞎几把写一点吧
     * 脸滚键盘
     */
    private static final String SALT = "slpiho4656s74s646s4dghs654h";
    public static String encryptPassword(String password){
        return Md5Util.md5(password+"{!i"+SALT+"i!}");
    }
}
