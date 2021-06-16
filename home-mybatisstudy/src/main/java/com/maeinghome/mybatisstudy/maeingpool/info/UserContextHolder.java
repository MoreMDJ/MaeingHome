package com.maeinghome.mybatisstudy.maeingpool.info;

public class UserContextHolder {
    public static final ThreadLocal<String> userName = new ThreadLocal<String>();

    public static String getDatabaseUrl(){
        String s = userName.get();
        return String.format("jdbc:mysql://172.16.5.14:3306/%s?allowMultiQueries=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai",getDatabaseFromThreadLocal());
    }
    public static String getDatabaseFromThreadLocal(){
        String s = userName.get();
        switch (s){
            case "wd":
                return "bclp_uat";
            case "dj":
                return "bclp_sit";
            case "dt":
                return "bclp_sit2";
            default:
                return "bclp";
        }
    }
}
