package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Prescription;
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

    @GetMapping("/listByPatient")
    public BaseResponse listByPatient(String userId) {
        return prescriptionService.queryByUserId(userId);
    }

    @GetMapping("/listByDoctor")
    public BaseResponse listByDoctor(String doctorId) {
        return prescriptionService.queryByDoctorId(doctorId);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody Prescription prescription) {
        return prescriptionService.update(prescription);
    }
}