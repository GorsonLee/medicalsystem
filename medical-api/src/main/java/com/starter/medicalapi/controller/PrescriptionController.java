package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Prescription;
import com.starter.medicalservice.service.PrescriptionService;
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
@RequestMapping("/prescription")
@Api(tags = "处方")
public class PrescriptionController {

    @Resource
    private PrescriptionService prescriptionService;

    @PostMapping("/insert")
    @ApiOperation("新增")
    public BaseResponse insert(@RequestBody Prescription prescription) {
        return prescriptionService.insert(prescription);
    }

    @PostMapping("/update")
    @ApiOperation("更新")
    public BaseResponse update(@RequestBody Prescription prescription) {
        return prescriptionService.update(prescription);
    }

    @GetMapping("/listByPatient")
    @ApiOperation("用户查看处方")
    public BaseResponse listByPatient(String userId) {
        return prescriptionService.queryByUserId(userId);
    }

    @GetMapping("/listByDoctor")
    @ApiOperation("医生查看处方")
    public BaseResponse listByDoctor(String doctorId) {
        return prescriptionService.queryByDoctorId(doctorId);
    }
}