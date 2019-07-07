package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.request.UserRegisterRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Manager;
import com.starter.medicalservice.service.ManagerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Resource
    private ManagerService managerService;

    @PostMapping("/register")
    public BaseResponse register(@RequestBody UserRegisterRequest request) {
        return managerService.register(request);
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody UserRegisterRequest request) {
        return managerService.login(request);
    }

    @GetMapping("/getUserInfo")
    public BaseResponse getUserInfo(String phone) {
        return managerService.queryByPhone(phone);
    }

    @GetMapping("/query")
    public BaseResponse query(String userId) {
        return managerService.query(userId);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody Manager manager) {
        return managerService.update(manager);
    }
}