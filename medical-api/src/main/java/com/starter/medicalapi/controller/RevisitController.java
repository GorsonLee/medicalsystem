package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Revisit;
import com.starter.medicalservice.service.RevisitService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/revisit")
public class RevisitController {

    @Resource
    private RevisitService revisitService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody Revisit revisit) {
        return revisitService.insert(revisit);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody Revisit revisit) {
        return revisitService.update(revisit);
    }

    @GetMapping("/list")
    public BaseResponse list(String doctorId) {
        return revisitService.queryByDoctorId(doctorId);
    }
}