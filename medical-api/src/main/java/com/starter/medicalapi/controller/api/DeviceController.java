package com.starter.medicalapi.controller.api;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Device;
import com.starter.medicalservice.service.DeviceService;
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
@RequestMapping("/device")
@Api(tags = "设备管理")
public class DeviceController {

    @Resource
    private DeviceService deviceService;

    @PostMapping("/insert")
    @ApiOperation("新增")
    public BaseResponse insert(@RequestBody Device request) {
        return deviceService.insert(request);
    }

    @PostMapping("/update")
    @ApiOperation("更新")
    public BaseResponse update(@RequestBody Device device) {
        return deviceService.update(device);
    }

    @GetMapping("/list")
    @ApiOperation("设备列表")
    public BaseResponse list(String userId) {
        return deviceService.queryByUserId(userId);
    }
}