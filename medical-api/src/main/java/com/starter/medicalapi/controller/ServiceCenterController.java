package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.ServiceCenter;
import com.starter.medicalservice.service.ServiceCenterService;
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
@RequestMapping("/serviceCenter")
public class ServiceCenterController {

    @Resource
    private ServiceCenterService serviceCenterService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody ServiceCenter serviceCenter) {
        return serviceCenterService.insert(serviceCenter);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody ServiceCenter serviceCenter) {
        return serviceCenterService.update(serviceCenter);
    }

    @GetMapping("/listByState")
    public BaseResponse listByState(Integer state) {
        return serviceCenterService.queryByState(state);
    }

    @GetMapping("/listByUserIdAndState")
    public BaseResponse listByUserIdAndState(String userId, Integer state) {
        return serviceCenterService.queryByUserIdAndState(userId, state);
    }

    @GetMapping("/listByManagerIdAndState")
    public BaseResponse listByManagerIdAndState(String managerId, Integer state) {
        return serviceCenterService.queryByManagerIdAndState(managerId, state);
    }
}