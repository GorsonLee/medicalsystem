package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.request.UserRegisterRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Elder;
import com.starter.medicalservice.service.ElderService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getUserInfo")
    public BaseResponse getUserInfo(String phone) {
        return elderService.queryByPhone(phone);
    }

    @GetMapping("/query")
    public BaseResponse query(String userId) {
        return elderService.query(userId);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody Elder elder) {
        return elderService.update(elder);
    }

}