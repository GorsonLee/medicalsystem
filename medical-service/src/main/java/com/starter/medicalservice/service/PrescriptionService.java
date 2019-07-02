package com.starter.medicalservice.service;

import com.alibaba.fastjson.JSONObject;
import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicaldao.entity.Prescription;
import com.starter.medicaldao.mapper.DoctorMapper;
import com.starter.medicaldao.mapper.PrescriptionMapper;
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
public class PrescriptionService {

    @Resource
    private PrescriptionMapper prescriptionMapper;
    @Resource
    private DoctorMapper doctorMapper;

    public BaseResponse insert(Prescription prescription) {
        Date now = new Date();
        prescription.setId(UUIDUtil.getUUID());
        prescription.setCreateTime(now);
        prescription.setModifyTime(now);
        int result = prescriptionMapper.insertSelective(prescription);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse update(Prescription prescription) {
        Date now = new Date();
        prescription.setModifyTime(now);
        int result = prescriptionMapper.updateByPrimaryKeySelective(prescription);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse<List<JSONObject>> queryByUserId(String userId) {
        List<Prescription> prescriptionList = prescriptionMapper.selectByUserId(userId);
        BaseResponse<List<JSONObject>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);

        List<JSONObject> resultList = prescriptionList.stream().map(item -> {
            Doctor doctor = doctorMapper.selectByPrimaryKey(item.getDoctorId());

            if (doctor != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", item.getId());
                jsonObject.put("doctorName", doctor.getName());
                jsonObject.put("title", item.getTitle());
                jsonObject.put("content", item.getContent());
                jsonObject.put("createTime", item.getCreateTime());
                return jsonObject;
            }

            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());

        response.setData(resultList);
        return response;
    }
}
