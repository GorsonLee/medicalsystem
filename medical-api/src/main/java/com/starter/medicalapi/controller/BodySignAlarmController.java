package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.BodySignAlarm;
import com.starter.medicalservice.service.BodySignAlarmService;
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
public class BodySignAlarmController {

    @Resource
    private BodySignAlarmService bodySignAlarmService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody BodySignAlarm bodySignAlarm) {
        return bodySignAlarmService.insert(bodySignAlarm);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody BodySignAlarm bodySignAlarm) {
        return bodySignAlarmService.update(bodySignAlarm);
    }

    @GetMapping("/queryByDoctorId")
    public BaseResponse queryByDoctorId(@NotNull String doctorId) {
        return bodySignAlarmService.queryByDoctorId(doctorId);
    }
}