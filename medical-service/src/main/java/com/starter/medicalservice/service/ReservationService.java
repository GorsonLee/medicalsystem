package com.starter.medicalservice.service;

import com.alibaba.fastjson.JSONObject;
import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicaldao.entity.Reservation;
import com.starter.medicaldao.mapper.DoctorMapper;
import com.starter.medicaldao.mapper.ReservationMapper;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@org.springframework.stereotype.Service
@Slf4j
public class ReservationService {

    @Resource
    private ReservationMapper reservationMapper;
    @Resource
    private DoctorMapper doctorMapper;

    public BaseResponse insert(Reservation reservation) {
        Date now = new Date();
        reservation.setId(UUIDUtil.getUUID());
        reservation.setState(1);
        reservation.setCreateTime(now);
        reservation.setModifyTime(now);
        int result = reservationMapper.insertSelective(reservation);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse update(Reservation reservation) {
        Date now = new Date();
        reservation.setModifyTime(now);
        int result = reservationMapper.updateByPrimaryKeySelective(reservation);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse<List<JSONObject>> queryByUserId(String userId) {
        List<Reservation> consultList = reservationMapper.selectByUserId(userId);
        BaseResponse<List<JSONObject>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);

        List<JSONObject> consultJsonList = consultList.stream().map(item -> {
            Doctor doctor = doctorMapper.selectByPrimaryKey(item.getDoctorId());

            if (doctor != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", item.getId());
                jsonObject.put("doctorId", item.getDoctorId());
                jsonObject.put("doctorName", doctor.getName());
                jsonObject.put("hospital", doctor.getHospital());
                jsonObject.put("department", doctor.getDepartment());

                jsonObject.put("instructions", item.getInstructions());
                jsonObject.put("state", item.getState());
                jsonObject.put("reserveTime", item.getReserveTime());
                return jsonObject;
            }

            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());

        response.setData(consultJsonList);
        return response;
    }

    public BaseResponse<List<JSONObject>> queryByDoctorId(String doctorId) {
        List<Reservation> consultList = reservationMapper.selectByDoctorId(doctorId);
        BaseResponse<List<JSONObject>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);

        List<JSONObject> consultJsonList = consultList.stream().map(item -> {
            Doctor doctor = doctorMapper.selectByPrimaryKey(item.getDoctorId());

            if (doctor != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", item.getId());
                jsonObject.put("doctorId", item.getDoctorId());
                jsonObject.put("doctorName", doctor.getName());
                jsonObject.put("hospital", doctor.getHospital());
                jsonObject.put("department", doctor.getDepartment());

                jsonObject.put("instructions", item.getInstructions());
                jsonObject.put("state", item.getState());
                jsonObject.put("reserveTime", item.getReserveTime());
                return jsonObject;
            }

            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());

        response.setData(consultJsonList);
        return response;
    }

}
