package com.starter.medicalapi.controller.api;

import com.starter.medicalcommon.vo.request.UserLoginRequest;
import com.starter.medicalcommon.vo.request.UserRegisterRequest;
import com.starter.medicalcommon.vo.request.UserUpdatePwdRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Manager;
import com.starter.medicalservice.service.ManagerService;
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
@CrossOrigin
@RestController
@RequestMapping("/manager")
@Api(tags = "服务管家")
public class ManagerController {

    @Resource
    private ManagerService managerService;

    @PostMapping("/register")
    @ApiOperation("注册用户")
    public BaseResponse register(@RequestBody UserRegisterRequest request) {
        return managerService.register(request);
    }

    @PostMapping("/login")
    @ApiOperation("登陆")
    public BaseResponse login(@RequestBody UserLoginRequest request) {
        return managerService.login(request);
    }

    @GetMapping("/queryByPhone")
    @ApiOperation("根据手机号获取用户信息")
    public BaseResponse queryByPhone(String phone) {
        return managerService.queryByPhone(phone);
    }

    @GetMapping("/query")
    @ApiOperation("根据用户ID获取用户信息")
    public BaseResponse query(String userId) {
        return managerService.query(userId);
    }

    @PostMapping("/update")
    @ApiOperation("更新")
    public BaseResponse update(@RequestBody Manager manager) {
        return managerService.update(manager);
    }

    @PostMapping("/updatePwd")
    @ApiOperation("更新密码")
    public BaseResponse update(@RequestBody UserUpdatePwdRequest request) {
        return managerService.updatePwd(request);
    }

}