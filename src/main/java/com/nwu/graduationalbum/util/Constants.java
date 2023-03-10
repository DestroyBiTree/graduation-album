package com.nwu.graduationalbum.util;

/**
 * @program: NwuGraduationAlbum
 * @description: 常量工具类
 * @author: TD.Miracle
 * @create: 2022-05-12 12:28
 **/
public class Constants {

    /*Redis常量*/
    public static final String USER_BULLET = "bullet:cache";
    public static final Long USER_BULLET_TTL = 10L;

    public static final String SHARE_NUM = "Share";
    public static final String STATISTIC_DAILY_PV = "PV:";
    public static final String STATISTIC_UV = "UV:";
    public static final String CLICK_NUM = "Click:";

    public static final String STUDENT_PREFIX = "NWUer:";
    public static final Long STUDENT_CACHE_TTL = 2L;

    /*系统常量*/
    public static final String SENSITIVE_FILE_PATH = "SensitiveWord.txt";
    public static final int SENSITIVE_MATCH_RULE_MIN = 1;
    public static final int SENSITIVE_MATCH_RULE_MAX = 2;

    /*正则表达式*/
    public static final String NUMBER_REGEX = "^\\d{9,11}$";

    /*token常量*/
    public static final long TOKEN_TTL = 7*24*60*60*1000;
    public static final String TOKEN_SIGN = "nwu_byc";
    public static final String TOKEN_PREFIX = "token:";
    public static final long TOKEN_CACHE_TTL = 1L;

    /*微信常量*/
    public static final String ACCESS_TOKEN = "accessToken";
    public static final long ACCESS_TOKEN_TTL = 7100;
    public static final String JS_API_TICKET = "jsapiTicket";
}
