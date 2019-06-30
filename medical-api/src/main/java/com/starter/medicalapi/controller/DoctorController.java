package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.request.DoctorRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
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
    public BaseResponse register(@RequestBody DoctorRequest request) {
        return doctorService.register(request);
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody DoctorRequest request) {
        return doctorService.login(request);
    }

    @GetMapping("/query")
    public BaseResponse query(String doctorId) {
        return doctorService.query(doctorId);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody DoctorRequest request) {
        return doctorService.update(request);
    }

    /**
     * 查询医生信息
     *
     * @param request
     * @return
     */
    @PostMapping("/queryDoctors")
    public BaseResponse queryDoctors(@RequestBody DoctorRequest request) {
        return doctorService.queryDoctors(request);
    }

}