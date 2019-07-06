package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.PrescriptionModel;
import com.starter.medicalservice.service.PrescriptionModelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/prescriptionModel")
public class PrescriptionModelController {

    @Resource
    private PrescriptionModelService prescriptionModelService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody PrescriptionModel prescriptionModel) {
        return prescriptionModelService.insert(prescriptionModel);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody PrescriptionModel prescriptionModel) {
        return prescriptionModelService.update(prescriptionModel);
    }

    @GetMapping("/list")
    public BaseResponse list(String doctorId) {
        return prescriptionModelService.list(doctorId);
    }
}