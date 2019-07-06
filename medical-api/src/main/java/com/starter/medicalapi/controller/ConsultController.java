package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Consult;
import com.starter.medicalservice.service.ConsultService;
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
public class ConsultController {

    @Resource
    private ConsultService consultService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody Consult consult) {
        return consultService.insert(consult);
    }

    @GetMapping("/listByPatient")
    public BaseResponse listByPatient(String userId) {
        return consultService.queryByUserId(userId);
    }

    @GetMapping("/listByDoctor")
    public BaseResponse listByDoctor(String doctorId) {
        return consultService.queryByDoctorId(doctorId);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody Consult consult) {
        return consultService.update(consult);
    }
}