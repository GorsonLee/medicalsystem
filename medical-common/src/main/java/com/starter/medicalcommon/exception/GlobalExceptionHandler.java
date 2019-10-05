package com.starter.medicalcommon.exception;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicalcommon.enums.MsgCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.net.BindException;

/**
 * 此类的描述是：全局系统异常处理
 *
 * @author Starter
 * @date 2018-04-26 23:44
 **/
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleBusinessException(BaseException ex) {
        log.warn("handleBusinessException", ex);
        return new BaseResponse(ex.getCode(), ex.getMsg());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            MissingServletRequestParameterException.class, //参数缺失
            HttpMessageNotReadableException.class, //参数解析失败
            MethodArgumentNotValidException.class, //参数验证失败
            BindException.class, //参数绑定失败
            ValidationException.class, //参数验证失败
    })
    public BaseResponse handleClientException(Exception ex) {
        log.error("handleClientException: ", ex);
        return new BaseResponse(MsgCodeEnum.UNKNOWN_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Throwable.class, //全局异常
            Exception.class, //通用异常
            RuntimeException.class, //运行时异常
            NullPointerException.class, //空指针异常
            ConstraintViolationException.class, //约束异常
    })
    public BaseResponse handleServerException(Throwable ex) {
        log.error("handleServerException: ", ex);
        return new BaseResponse(MsgCodeEnum.UNKNOWN_ERROR);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("handleHttpRequestMethodNotSupportedException: ", ex);
        return new BaseResponse(MsgCodeEnum.UNKNOWN_ERROR);
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public BaseResponse handleHttpMediaTypeNotSupportedException(Exception ex) {
        log.error("handleHttpMediaTypeNotSupportedException: ", ex);
        return new BaseResponse(MsgCodeEnum.UNKNOWN_ERROR);
    }
}