package com.starter.medicalapi.controller.api;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Revisit;
import com.starter.medicalservice.service.RevisitService;
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
@RequestMapping("/revisit")
@Api(tags = "病人回访")
public class RevisitController {

    @Resource
    private RevisitService revisitService;

    @PostMapping("/insert")
    @ApiOperation("新增")
    public BaseResponse insert(@RequestBody Revisit revisit) {
        return revisitService.insert(revisit);
    }

    @PostMapping("/update")
    @ApiOperation("更新")
    public BaseResponse update(@RequestBody Revisit revisit) {
        return revisitService.update(revisit);
    }

    @GetMapping("/list")
    @ApiOperation("医生查看回访")
    public BaseResponse list(String doctorId) {
        return revisitService.queryByDoctorId(doctorId);
    }
}