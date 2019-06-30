package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.request.UserRegisterRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicalservice.service.ElderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/elder")
public class ElderController {

    @Resource
    private ElderService elderService;

    @PostMapping("/register")
    public BaseResponse register(@RequestBody UserRegisterRequest request) {
        return elderService.register(request);
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody UserRegisterRequest request) {
        return elderService.login(request);
    }

    //todo 查询用户信息，修改用户信息
}