package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.request.UserLoginRequest;
import com.starter.medicalcommon.vo.request.UserRegisterRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Younger;
import com.starter.medicalservice.service.YoungerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/younger")
@Api(tags = "晚辈")
public class YoungerController {

    @Resource
    private YoungerService youngerService;

    @PostMapping("/register")
    @ApiOperation("注册用户")
    public BaseResponse register(@RequestBody UserRegisterRequest request) {
        return youngerService.register(request);
    }

    @PostMapping("/login")
    @ApiOperation("登陆")
    public BaseResponse login(@RequestBody UserLoginRequest request) {
        return youngerService.login(request);
    }

    @GetMapping("/getUserInfo")
    @ApiOperation("根据手机号获取用户信息")
    public BaseResponse getUserInfo(String phone) {
        return youngerService.queryByPhone(phone);
    }

    @GetMapping("/query")
    @ApiOperation("根据用户ID获取用户信息")
    public BaseResponse query(String userId) {
        return youngerService.query(userId);
    }

    @PostMapping("/update")
    @ApiOperation("更新")
    public BaseResponse update(@RequestBody Younger younger) {
        return youngerService.update(younger);
    }
}