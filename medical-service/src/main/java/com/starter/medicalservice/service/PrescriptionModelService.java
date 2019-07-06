package com.starter.medicalservice.service;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.PrescriptionModel;
import com.starter.medicaldao.mapper.PrescriptionModelMapper;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@org.springframework.stereotype.Service
@Slf4j
public class PrescriptionModelService {

    @Resource
    private PrescriptionModelMapper prescriptionModelMapper;

    public BaseResponse insert(PrescriptionModel prescriptionModel) {
        Date now = new Date();
        prescriptionModel.setId(UUIDUtil.getUUID());
        prescriptionModel.setCreateTime(now);
        prescriptionModel.setModifyTime(now);
        int result = prescriptionModelMapper.insertSelective(prescriptionModel);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse update(PrescriptionModel prescriptionModel) {
        Date now = new Date();
        prescriptionModel.setModifyTime(now);
        int result = prescriptionModelMapper.updateByPrimaryKeySelective(prescriptionModel);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse<List<PrescriptionModel>> list(String doctorId) {
        BaseResponse<List<PrescriptionModel>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(prescriptionModelMapper.selectByDoctorId(doctorId));
        return response;
    }
}
