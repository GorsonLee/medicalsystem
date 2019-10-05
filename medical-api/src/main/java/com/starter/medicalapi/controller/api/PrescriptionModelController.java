package com.starter.medicalapi.controller.api;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.PrescriptionModel;
import com.starter.medicalservice.service.PrescriptionModelService;
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
@CrossOrigin
@RestController
@RequestMapping("/prescriptionModel")
@Api(tags = "处方模板")
public class PrescriptionModelController {

    @Resource
    private PrescriptionModelService prescriptionModelService;

    @PostMapping("/insert")
    @ApiOperation("新增")
    public BaseResponse insert(@RequestBody PrescriptionModel prescriptionModel) {
        return prescriptionModelService.insert(prescriptionModel);
    }

    @PostMapping("/update")
    @ApiOperation("更新")
    public BaseResponse update(@RequestBody PrescriptionModel prescriptionModel) {
        return prescriptionModelService.update(prescriptionModel);
    }

    @GetMapping("/list")
    @ApiOperation("医生查看处方模板")
    public BaseResponse list(String doctorId) {
        return prescriptionModelService.list(doctorId);
    }
}