package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Contract;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicalservice.service.ContractService;
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
    @Resource
    private ContractService contractService;

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

    @GetMapping("/getDoctorInfo")
    public BaseResponse getDoctorInfo(String phone) {
        return doctorService.queryByPhone(phone);
    }

    /**
     * 和医生签约
     *
     * @param contract
     * @return
     */
    @PostMapping("/contract")
    public BaseResponse contract(@RequestBody Contract contract) {
        return contractService.insert(contract);
    }

    /**
     * 签约医生列表
     *
     * @param userId
     * @return
     */
    @GetMapping("/getContractDoctors")
    public BaseResponse getContractDoctors(String userId) {
        return contractService.queryContractDoctors(userId);
    }
}