package com.starter.medicalapi.controller.manage.basic;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.DateUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Consult;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.entity.Reservation;
import com.starter.medicaldao.entity.dto.ConsultDto;
import com.starter.medicaldao.entity.dto.ReservationDto;
import com.starter.medicaldao.mapper.ConsultMapper;
import com.starter.medicaldao.mapper.DoctorMapper;
import com.starter.medicaldao.mapper.ElderMapper;
import com.starter.medicaldao.mapper.ReservationMapper;
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

}
