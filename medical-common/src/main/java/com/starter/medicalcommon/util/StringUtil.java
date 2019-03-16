package com.starter.medicalcommon.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-03-16 16:32
 **/
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
     * 将驼峰式转成下划线式字符串
     *
     * @param camelString 驼峰式字符串
     * @return 下划线式字符串
     */
    public static String camel2Underline(String camelString) {
        if (StringUtils.isEmpty(camelString)) {
            return "";
        }

        camelString = String.valueOf(camelString.charAt(0)).toUpperCase().concat(camelString.substring(1));
        StringBuilder sb = new StringBuilder();
        Matcher matcher = ALPHABET_PATTERN.matcher(camelString);

        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end() == camelString.length() ? "" : "_");
        }

        return sb.toString();
    }
}