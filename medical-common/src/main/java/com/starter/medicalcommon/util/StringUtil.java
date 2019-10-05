package com.starter.medicalcommon.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-03-16 16:32
 **/
@Slf4j
public class StringUtil {
    private static final Pattern ALPHABET_PATTERN = Pattern.compile("[A-Z]([a-z\\d]+)?");

    /**
     * 将下划线式转换成驼峰式字符串
     *
     * @param underlineString 下划线式字符串
     * @param smallCamel      驼峰字符串是否区分大小写
     * @return 驼峰式字符串
     */
    public static String underlineToCamel(String underlineString, boolean smallCamel) {
        if (StringUtils.isEmpty(underlineString)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        boolean upper = !smallCamel;

        for (Character c : underlineString.toCharArray()) {
            if ('_' == c) {
                upper = true;
            } else {
                sb.append(upper ? Character.toUpperCase(c) : c);
                upper = false;
            }
        }

        return sb.toString();
    }




    /**
     * 生日日期string转int
     */
    public static Integer stringBirth2Int(String birthString) {
        String briths = birthString.replaceAll("-","");
        Integer birth = 0;
        try {
            birth = Integer.parseInt(briths);
        }catch (Exception e) {
            log.error("生日日期转换异常 e:{}", e);
        }
        return birth;
    }
}