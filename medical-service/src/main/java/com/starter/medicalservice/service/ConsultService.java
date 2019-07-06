package com.starter.medicalservice.service;

import com.alibaba.fastjson.JSONObject;
import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Consult;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicaldao.mapper.ConsultMapper;
import com.starter.medicaldao.mapper.DoctorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
@Service
@Slf4j
public class ConsultService {

    @Resource
    private ConsultMapper consultMapper;
    @Resource
    private DoctorMapper doctorMapper;

    public BaseResponse insert(Consult consult) {
        Date now = new Date();
        consult.setId(UUIDUtil.getUUID());
        consult.setState(0);
        consult.setCreateTime(now);
        consult.setModifyTime(now);
        int result = consultMapper.insertSelective(consult);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse update(Consult consult) {
        Date now = new Date();
        consult.setModifyTime(now);
        int result = consultMapper.updateByPrimaryKeySelective(consult);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse<List<JSONObject>> queryByUserId(String userId) {
        List<Consult> consultList = consultMapper.selectByUserId(userId);
        BaseResponse<List<JSONObject>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);

        List<JSONObject> consultJsonList = consultList.stream().map(item -> {
            Doctor doctor = doctorMapper.selectByPrimaryKey(item.getDoctorId());

            if (doctor != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", item.getId());
                jsonObject.put("doctorId", doctor.getId());
                jsonObject.put("doctorName", doctor.getName());
                jsonObject.put("symptoms", item.getSymptoms());
                jsonObject.put("description", item.getDescription());
                jsonObject.put("recordUrls", item.getRecordUrls());
                jsonObject.put("doctorReply", item.getDoctorReply());
                jsonObject.put("state", item.getState());
                jsonObject.put("replyTime", item.getReplyTime());
                return jsonObject;
            }

            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());

        response.setData(consultJsonList);
        return response;
    }
}
