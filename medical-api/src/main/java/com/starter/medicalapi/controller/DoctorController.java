package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.request.UserLoginRequest;
import com.starter.medicalcommon.vo.request.UserRegisterRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Contract;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicalservice.service.ContractService;
import com.starter.medicalservice.service.DoctorService;
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
@RequestMapping("/doctor")
@Api(tags = "医生")
public class DoctorController {

    @Resource
    private DoctorService doctorService;
    @Resource
    private ContractService contractService;

    @PostMapping("/register")
    @ApiOperation("注册")
    public BaseResponse register(@RequestBody UserRegisterRequest request) {
        return doctorService.register(request);
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    public BaseResponse login(@RequestBody UserLoginRequest request) {
        return doctorService.login(request);
    }

    @GetMapping("/query")
    @ApiOperation("获取医生信息")
    public BaseResponse query(String doctorId) {
        return doctorService.query(doctorId);
    }

    @PostMapping("/update")
    @ApiOperation("更新医生信息")
    public BaseResponse update(@RequestBody Doctor doctor) {
        return doctorService.update(doctor);
    }

    @PostMapping("/queryDoctors")
    @ApiOperation("按条件查询医生")
    public BaseResponse queryDoctors(@RequestBody Doctor doctor) {
        return doctorService.queryDoctors(doctor);
    }

    @GetMapping("/getDoctorInfo")
    @ApiOperation("根据医生手机号查询")
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
    @ApiOperation("和医生签约")
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
    @ApiOperation("签约医生列表")
    public BaseResponse getContractDoctors(String userId) {
        return contractService.queryContractDoctors(userId);
    }

    /**
     * 签约病人列表
     *
     * @param doctorId
     * @return
     */
    @GetMapping("/getContractPatients")
    @ApiOperation("签约病人列表")
    public BaseResponse getContractPatients(String doctorId) {
        return contractService.queryContractPatients(doctorId);
    }
}