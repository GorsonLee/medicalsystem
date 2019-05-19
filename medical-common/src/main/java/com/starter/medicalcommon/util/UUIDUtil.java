package com.starter.medicalcommon.util;

import java.util.UUID;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 16:32
 **/
public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
