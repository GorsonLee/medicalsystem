package com.starter.medicalservice.service;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.BodySignAlarm;
import com.starter.medicaldao.mapper.BodySignAlarmMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@Service
@Slf4j
public class BodySignAlarmService {

    @Resource
    private BodySignAlarmMapper bodySignAlarmMapper;

    public BaseResponse insert(BodySignAlarm bodySignAlarm) {
        Date now = new Date();
        bodySignAlarm.setId(UUIDUtil.getUUID());
        bodySignAlarm.setCreateTime(now);
        bodySignAlarm.setModifyTime(now);
        int result = bodySignAlarmMapper.insertSelective(bodySignAlarm);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse update(BodySignAlarm bodySignAlarm) {
        Date now = new Date();
        bodySignAlarm.setModifyTime(now);
        int result = bodySignAlarmMapper.updateByPrimaryKeySelective(bodySignAlarm);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse<List<BodySignAlarm>> queryByDoctorId(String doctorId) {
        List<BodySignAlarm> bodySignAlarmList = bodySignAlarmMapper.selectByDoctorId(doctorId);
        BaseResponse<List<BodySignAlarm>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(bodySignAlarmList);
        return response;
    }
}