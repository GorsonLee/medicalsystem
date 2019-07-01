package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicalservice.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Resource
    private DoctorService doctorService;

    @PostMapping("/register")
    public BaseResponse register(@RequestBody Doctor doctor) {
        return doctorService.register(doctor);
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody Doctor doctor) {
        return doctorService.login(doctor);
    }

    @GetMapping("/query")
    public BaseResponse query(String doctorId) {
        return doctorService.query(doctorId);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody Doctor doctor) {
        return doctorService.update(doctor);
    }

    @PostMapping("/queryDoctors")
    public BaseResponse queryDoctors(@RequestBody Doctor doctor) {
        return doctorService.queryDoctors(doctor);
    }

    @GetMapping("/getUserInfo")
    public BaseResponse getUserInfo(String phone) {
        return doctorService.queryByPhone(phone);
    }

}