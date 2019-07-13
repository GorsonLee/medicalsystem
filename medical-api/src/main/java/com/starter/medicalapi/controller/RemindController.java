package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Device;
import com.starter.medicaldao.entity.Remind;
import com.starter.medicalservice.service.DeviceService;
import com.starter.medicalservice.service.RemindService;
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
@RequestMapping("/remind")
@Api(tags = "提醒信息")
public class RemindController {

    @Resource
    private RemindService remindService;

    @PostMapping("/insert")
    @ApiOperation("新增")
    public BaseResponse insert(@RequestBody Remind remind) {
        return remindService.insert(remind);
    }

    @PostMapping("/update")
    @ApiOperation("更新")
    public BaseResponse update(@RequestBody Remind remind) {
        return remindService.update(remind);
    }

    @GetMapping("/list")
    @ApiOperation("用户查看提醒信息")
    public BaseResponse list(String userId) {
        return remindService.queryByUserId(userId);
    }
}