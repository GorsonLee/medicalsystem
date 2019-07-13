package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Reservation;
import com.starter.medicalservice.service.ReservationService;
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
@RequestMapping("/reservation")
@Api(tags = "医生预约")
public class ReservationController {

    @Resource
    private ReservationService reservationService;

    @PostMapping("/insert")
    @ApiOperation("新增")
    public BaseResponse insert(@RequestBody Reservation reservation) {
        return reservationService.insert(reservation);
    }

    @PostMapping("/update")
    @ApiOperation("更新")
    public BaseResponse update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }

    @GetMapping("/listByPatient")
    @ApiOperation("用户查看预约")
    public BaseResponse listByPatient(String userId) {
        return reservationService.queryByUserId(userId);
    }

    @GetMapping("/listByDoctor")
    @ApiOperation("医生查看预约")
    public BaseResponse listByDoctor(String doctorId) {
        return reservationService.queryByDoctorId(doctorId);
    }

}