package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Consult;
import com.starter.medicalservice.service.ConsultService;
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
@RequestMapping("/consult")
@Api(tags = "用户咨询")
public class ConsultController {

    @Resource
    private ConsultService consultService;

    @PostMapping("/insert")
    @ApiOperation("新增")
    public BaseResponse insert(@RequestBody Consult consult) {
        return consultService.insert(consult);
    }

    @PostMapping("/update")
    @ApiOperation("更新")
    public BaseResponse update(@RequestBody Consult consult) {
        return consultService.update(consult);
    }

    @GetMapping("/listByPatient")
    @ApiOperation("病人查看咨询")
    public BaseResponse listByPatient(String userId) {
        return consultService.queryByUserId(userId);
    }

    @GetMapping("/listByDoctor")
    @ApiOperation("医生查看咨询")
    public BaseResponse listByDoctor(String doctorId) {
        return consultService.queryByDoctorId(doctorId);
    }

}