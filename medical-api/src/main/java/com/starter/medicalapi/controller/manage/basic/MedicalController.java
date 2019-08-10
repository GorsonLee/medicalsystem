package com.starter.medicalapi.controller.manage.basic;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.DateUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.*;
import com.starter.medicaldao.entity.dto.ConsultDto;
import com.starter.medicaldao.entity.dto.PrescriptionDto;
import com.starter.medicaldao.entity.dto.ReservationDto;
import com.starter.medicaldao.entity.dto.RevisitDto;
import com.starter.medicaldao.mapper.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-07-14 1:00
 **/
@RestController
@RequestMapping("/manage/medical")
@Api(tags = "医疗服务")
public class MedicalController {

    @Resource
    private ReservationMapper reservationMapper;
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private ConsultMapper consultMapper;
    @Resource
    private RevisitMapper revisitMapper;
    @Resource
    private PrescriptionMapper prescriptionMapper;

    @GetMapping("/reserveList")
    @ApiOperation("挂号预约列表")
    public BaseResponse reserveList(Integer state,
                                  Integer offset,
                                  Integer pageSize) {
        BaseResponse<List<ReservationDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<ReservationDto> resultList= new ArrayList<>();

        List<Reservation> reservationList = reservationMapper.selectByState(state, offset, pageSize);
        if (!CollectionUtils.isEmpty(reservationList)) {
            reservationList.stream().filter(reservation -> reservation != null).forEach(reservation -> {
                ReservationDto dto = new ReservationDto();
                BeanUtils.copyProperties(reservation, dto);
                if (reservation.getReserveTime() != null) {
                    dto.setReserveTimeStr(DateUtil.dateToString(reservation.getReserveTime()));
                }
                // 老人姓名和电话
                Elder elder = elderMapper.selectByPrimaryKey(reservation.getUserId());
                if (elder != null) {
                    dto.setUserName(elder.getName());
                    dto.setUserPhone(elder.getPhone());
                }

                // 医生姓名和电话
                Doctor doctor = doctorMapper.selectByPrimaryKey(reservation.getDoctorId());
                if (doctor != null) {
                    dto.setDoctorName(doctor.getName());
                    dto.setDoctorPhone(doctor.getPhone());
                }
                resultList.add(dto);
            });
        }

        response.setData(resultList);
        return response;
    }

    @GetMapping("/consultList")
    @ApiOperation("咨询管理列表")
    public BaseResponse reserveList(Integer offset,
                                    Integer pageSize) {
        BaseResponse<List<ConsultDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<ConsultDto> resultList= new ArrayList<>();

        List<Consult> consultList = consultMapper.selectAll(offset, pageSize);
        if (!CollectionUtils.isEmpty(consultList)) {
            consultList.stream().filter(consult -> consult != null).forEach(consult -> {
                ConsultDto dto = new ConsultDto();
                BeanUtils.copyProperties(consult, dto);
                if (consult.getCreateTime() != null) {
                    dto.setCreateTimeStr(DateUtil.dateToString(consult.getCreateTime()));
                }
                // 老人姓名和电话
                Elder elder = elderMapper.selectByPrimaryKey(consult.getUserId());
                if (elder != null) {
                    dto.setUserName(elder.getName());
                    dto.setUserPhone(elder.getPhone());
                }

                // 医生姓名和电话
                Doctor doctor = doctorMapper.selectByPrimaryKey(consult.getDoctorId());
                if (doctor != null) {
                    dto.setDoctorName(doctor.getName());
                    dto.setDoctorPhone(doctor.getPhone());
                }
                resultList.add(dto);
            });
        }

        response.setData(resultList);
        return response;
    }

    @GetMapping("/revisitList")
    @ApiOperation("回访管理列表")
    public BaseResponse revisitList(String method, Integer offset,
                                    Integer pageSize) {
        BaseResponse<List<RevisitDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<RevisitDto> resultList= new ArrayList<>();

        List<Revisit> revisitList = revisitMapper.selectByMethod(method, offset, pageSize);
        if (!CollectionUtils.isEmpty(revisitList)) {
            revisitList.stream().filter(revisit -> revisit != null).forEach(revisit -> {
                RevisitDto dto = new RevisitDto();
                BeanUtils.copyProperties(revisit, dto);
                if (revisit.getVisitTime() != null) {
                    dto.setVisitTimeStr(DateUtil.dateToString(revisit.getVisitTime()));
                }
                // 老人姓名和电话
                Elder elder = elderMapper.selectByPrimaryKey(revisit.getUserId());
                if (elder != null) {
                    dto.setUserName(elder.getName());
                    dto.setUserPhone(elder.getPhone());
                }

                // 医生姓名和电话
                Doctor doctor = doctorMapper.selectByPrimaryKey(revisit.getDoctorId());
                if (doctor != null) {
                    dto.setDoctorName(doctor.getName());
                    dto.setDoctorPhone(doctor.getPhone());
                }
                resultList.add(dto);
            });
        }

        response.setData(resultList);
        return response;
    }

    @GetMapping("/prescriptionList")
    @ApiOperation("处方管理列表")
    public BaseResponse prescriptionList(Integer offset,
                                    Integer pageSize) {
        BaseResponse<List<PrescriptionDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<PrescriptionDto> resultList= new ArrayList<>();

        List<Prescription> prescriptionList = prescriptionMapper.selectAll(offset, pageSize);
        if (!CollectionUtils.isEmpty(prescriptionList)) {
            prescriptionList.stream().filter(prescription -> prescription != null).forEach(prescription -> {
                PrescriptionDto dto = new PrescriptionDto();
                BeanUtils.copyProperties(prescription, dto);
                if (prescription.getCreateTime() != null) {
                    dto.setCreateTimeStr(DateUtil.dateToString(prescription.getCreateTime()));
                }
                // 老人姓名和电话
                Elder elder = elderMapper.selectByPrimaryKey(prescription.getUserId());
                if (elder != null) {
                    dto.setUserName(elder.getName());
                    dto.setUserPhone(elder.getPhone());
                }

                // 医生姓名和电话
                Doctor doctor = doctorMapper.selectByPrimaryKey(prescription.getDoctorId());
                if (doctor != null) {
                    dto.setDoctorName(doctor.getName());
                    dto.setDoctorPhone(doctor.getPhone());
                }
                resultList.add(dto);
            });
        }

        response.setData(resultList);
        return response;
    }

}
