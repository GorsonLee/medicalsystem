package com.starter.medicalcommon.constant;

import java.time.format.DateTimeFormatter;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-03-16 16:32
 **/
public class Constant {

    /**
     * 完整的时间格式
     */
    public static final String DATE_PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间转换器
     */
    public static final DateTimeFormatter DATE_PATTERN_FORMAT_FULL = DateTimeFormatter.ofPattern(DATE_PATTERN_FULL);
}
