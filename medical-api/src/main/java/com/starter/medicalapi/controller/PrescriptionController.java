package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Consult;
import com.starter.medicaldao.entity.Prescription;
import com.starter.medicalservice.service.ConsultService;
import com.starter.medicalservice.service.PrescriptionService;
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
public class PrescriptionController {

    @Resource
    private PrescriptionService prescriptionService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody Prescription prescription) {
        return prescriptionService.insert(prescription);
    }

    @GetMapping("/list")
    public BaseResponse list(String userId) {
        return prescriptionService.queryByUserId(userId);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody Prescription prescription) {
        return prescriptionService.update(prescription);
    }
}