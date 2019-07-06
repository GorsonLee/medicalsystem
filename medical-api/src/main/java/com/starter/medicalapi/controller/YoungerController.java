package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.request.UserRegisterRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Younger;
import com.starter.medicalservice.service.YoungerService;
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
public class YoungerController {

    @Resource
    private YoungerService youngerService;

    @PostMapping("/register")
    public BaseResponse register(@RequestBody UserRegisterRequest request) {
        return youngerService.register(request);
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody UserRegisterRequest request) {
        return youngerService.login(request);
    }

    @GetMapping("/getUserInfo")
    public BaseResponse getUserInfo(String phone) {
        return youngerService.queryByPhone(phone);
    }

    @GetMapping("/query")
    public BaseResponse query(String userId) {
        return youngerService.query(userId);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody Younger younger) {
        return youngerService.update(younger);
    }
}