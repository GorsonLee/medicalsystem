package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Consult;
import com.starter.medicaldao.entity.Reservation;
import com.starter.medicalservice.service.ConsultService;
import com.starter.medicalservice.service.ReservationService;
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
public class ReservationController {

    @Resource
    private ReservationService reservationService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody Reservation reservation) {
        return reservationService.insert(reservation);
    }

    @GetMapping("/listByPatient")
    public BaseResponse listByPatient(String userId) {
        return reservationService.queryByUserId(userId);
    }

    @GetMapping("/listByDoctor")
    public BaseResponse listByDoctor(String doctor) {
        return reservationService.queryByDoctorId(doctor);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }
}