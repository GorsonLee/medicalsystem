package com.starter.medicalcommon.enums;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-03-16 22:02
 **/
public enum MsgCodeEnum {

    /**
     * 处理成功
     */
    SUCCESS(200, "success"),

    PARAM_INVALID(-400, "参数错误"),

    OPERATION_FAIL_ERROR(-410, "操作失败"),
    RESOURCE_EXIST_ERROR(-411, "资源重复"),

    /**
     * 服务异常
     */
    SYSTEM_ERROR(-500, "服务异常"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR(-999, "未知异常"),

    /********** 用户模块错误码，范围1000~1099 **********/
    USER_EXIST_ERROR(-1000, "用户已经存在"),
    USER_PASSWORD_ERROR(-1001, "密码错误"),
    USER_REGISTER_ERROR(-1002, "用户注册失败"),
    ELDER_NOT_EXISTS(-1003, "老人账号不存在"),
    MANAGER_NOT_EXISTS(-1004, "服务人眼账号不存在"),
    USER_NOT_FOUND_ERROR(-1005, "用户不存在");


    /********** 健康档案错误码，范围1100~1199 **********/
    /**
     * 健康档案已经存在
     */

    private int code;

    private String msg;

    MsgCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}