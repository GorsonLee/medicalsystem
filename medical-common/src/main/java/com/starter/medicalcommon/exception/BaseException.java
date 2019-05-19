package com.starter.medicalcommon.exception;


import com.starter.medicalcommon.enums.MsgCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 此类的描述是：业务异常类
 *
 * @author Starter
 * @date 2019-03-16 22:02
 **/
@Data
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private int code;

    private String msg;

    public BaseException(MsgCodeEnum msgCodeEnum) {
        this.code = msgCodeEnum.getCode();
        this.msg = msgCodeEnum.getMsg();
    }
}