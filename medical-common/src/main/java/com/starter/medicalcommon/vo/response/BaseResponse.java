package com.starter.medicalcommon.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.starter.medicalcommon.enums.MsgCodeEnum;
import lombok.Data;

/**
 * 此类的描述是：响应类
 *
 * @author Starter
 * @date 2019-03-16 22:02
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回异常信息
     */
    private String error;
    /**
     * 响应时间戳
     */
    private String timestamp;

    /**
     * 泛型数据
     */
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(MsgCodeEnum msgCodeEnum) {
        this.code = msgCodeEnum.getCode();
        this.message = msgCodeEnum.getMsg();
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    /**
     * 成功响应
     *
     * @return 成功响应
     */
    public static BaseResponse successResponse() {
        return new BaseResponse(MsgCodeEnum.SUCCESS);
    }

    /**
     * 系统异常错误
     *
     * @return 系统异常错误
     */
    public static BaseResponse failResponse() {
        return new BaseResponse(MsgCodeEnum.SYSTEM_ERROR);
    }
}