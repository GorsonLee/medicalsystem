package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.BodySignAlarm;
import com.starter.medicalservice.service.BodySignAlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/bodySignAlarm")
@Api(tags = "体征告警")
public class BodySignAlarmController {

    @Resource
    private BodySignAlarmService bodySignAlarmService;

    @PostMapping("/insert")
    @ApiOperation("新增")
    public BaseResponse insert(@RequestBody BodySignAlarm bodySignAlarm) {
        return bodySignAlarmService.insert(bodySignAlarm);
    }

    @PostMapping("/update")
    @ApiOperation("更新")
    public BaseResponse update(@RequestBody BodySignAlarm bodySignAlarm) {
        return bodySignAlarmService.update(bodySignAlarm);
    }

    @GetMapping("/queryByDoctorId")
    @ApiOperation("查看体征告警")
    public BaseResponse queryByDoctorId(@NotNull String doctorId) {
        return bodySignAlarmService.queryByDoctorId(doctorId);
    }
}