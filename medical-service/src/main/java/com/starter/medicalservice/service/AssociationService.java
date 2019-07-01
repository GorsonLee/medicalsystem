package com.starter.medicalservice.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.request.AssociationRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Association;
import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.entity.Remind;
import com.starter.medicaldao.mapper.AssociationMapper;
import com.starter.medicaldao.mapper.ElderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@Service
@Slf4j
public class AssociationService {

    @Resource
    private AssociationMapper associationMapper;
    @Resource
    private ElderMapper elderMapper;

    /**
     * 关联老人
     *
     * @param
     * @param
     * @param
     * @param
     * @return
     */
    public BaseResponse associate(AssociationRequest associationRequest) {
        Elder elder = elderMapper.selectByPhoneAndNameAndIdentity(associationRequest.getAssociatePhone(),
                associationRequest.getAssociateName(),
                associationRequest.getAssociateIdentity());

        if (elder == null) {
            return new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
        }

        Date now = new Date();
        Association association = new Association();
        association.setId(UUIDUtil.getUUID());
        association.setUserId(associationRequest.getUserId());
        association.setAssociateUserId(elder.getId());
        association.setState(0);
        association.setCreateTime(now);
        association.setModifyTime(now);
        int result = associationMapper.insertSelective(association);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    /**
     * 查询关联的老人
     *
     * @param userId 用户ID
     * @return
     */
    public BaseResponse<List<JSONObject>> queryAssociateElder(String userId) {
        List<Association> associationList = associationMapper.selectByUserId(userId);
        List<JSONObject> elderList = new ArrayList<>();

        associationList.forEach(item -> {
            Elder elder = elderMapper.selectByPrimaryKey(item.getAssociateUserId());

            if (elder != null) {
                JSONObject elderJson = new JSONObject();
                elderJson.put("id", elder.getId());
                elderJson.put("name", elder.getName());
                elderJson.put("state", item.getState());
                elderList.add(elderJson);
            }
        });

        BaseResponse<List<JSONObject>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(elderList);
        return response;
    }

    public BaseResponse update(Association association) {
        association.setModifyTime(new Date());
        int result = associationMapper.updateByPrimaryKeySelective(association);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }
}