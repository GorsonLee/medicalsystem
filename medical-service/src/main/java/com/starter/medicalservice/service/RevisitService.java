package com.starter.medicalservice.service;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.entity.Revisit;
import com.starter.medicaldao.mapper.ElderMapper;
import com.starter.medicaldao.mapper.RevisitMapper;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@Service
@Slf4j
public class RevisitService {

    @Resource
    private RevisitMapper revisitMapper;
    @Resource
    private ElderMapper elderMapper;

    public BaseResponse insert(Revisit revisit) {
        Date now = new Date();
        revisit.setId(UUIDUtil.getUUID());
        revisit.setCreateTime(now);
        revisit.setModifyTime(now);
        int result = revisitMapper.insertSelective(revisit);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse update(Revisit revisit) {
        revisit.setModifyTime(new Date());
        int result = revisitMapper.updateByPrimaryKeySelective(revisit);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse<List<JSONObject>> queryByDoctorId(String doctorId) {
        List<Revisit> revisitList = revisitMapper.selectByDoctorId(doctorId);
        BaseResponse<List<JSONObject>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);

        List<JSONObject> jsonList = revisitList.stream()
                .map(item -> {
                    Elder elder = elderMapper.selectByPrimaryKey(item.getUserId());
                    JSONObject revisit = new JSONObject();
                    revisit.put("id", item.getId());
                    revisit.put("userId", item.getUserId());
                    revisit.put("userName", elder.getName());
                    revisit.put("method", item.getMethod());
                    revisit.put("content", item.getContent());
                    revisit.put("visitTime", item.getVisitTime());
                    return revisit;
                }).collect(Collectors.toList());

        response.setData(jsonList);
        return response;
    }
}