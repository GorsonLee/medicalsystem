package com.starter.medicalservice.service;

import com.alibaba.fastjson.JSONObject;
import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.request.AssociationRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Association;
import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.entity.Younger;
import com.starter.medicaldao.mapper.AssociationMapper;
import com.starter.medicaldao.mapper.ElderMapper;
import com.starter.medicaldao.mapper.YoungerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class AssociationService {

    @Resource
    private AssociationMapper associationMapper;
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private YoungerMapper youngerMapper;

    /**
     * 关联老人
     *
     * @param associationRequest
     * @return
     */
    public BaseResponse associate(AssociationRequest associationRequest) {
        Elder elder = elderMapper.selectByPhoneAndNameAndIdentity(associationRequest.getAssociatePhone(),
                associationRequest.getAssociateName(),
                associationRequest.getAssociateIdentity());

        if (elder == null) {
            return new BaseResponse(MsgCodeEnum.USER_NOT_FOUND_ERROR);
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
     * 查询我关联的老人
     *
     * @param userId 用户ID
     * @return
     */
    public BaseResponse<List<JSONObject>> queryMyAssociateElder(String userId) {
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


    /**
     * 查询关联我的人
     *
     * @param userId
     * @return
     */
    public BaseResponse<List<JSONObject>> queryAssociateMeUser(String userId) {
        List<Association> associationList = associationMapper.selectByAssociateUserId(userId);
        List<JSONObject> userList = new ArrayList<>();

        associationList.forEach(item -> {
            //先从老人表中查找关注我的人
            Elder elder = elderMapper.selectByPrimaryKey(item.getUserId());

            if (elder != null) {
                JSONObject elderJson = new JSONObject();
                elderJson.put("id", elder.getId());
                elderJson.put("name", elder.getName());
                elderJson.put("state", item.getState());
                userList.add(elderJson);
            } else {
                //再从年轻人表中查找关注我的人
                Younger younger = youngerMapper.selectByPrimaryKey(item.getUserId());

                if (younger != null) {
                    JSONObject youngerJson = new JSONObject();
                    youngerJson.put("id", elder.getId());
                    youngerJson.put("name", elder.getName());
                    youngerJson.put("state", item.getState());
                    userList.add(youngerJson);
                }
            }
        });

        BaseResponse<List<JSONObject>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(userList);
        return response;
    }


    public BaseResponse update(Association association) {
        association.setModifyTime(new Date());
        int result = associationMapper.updateByPrimaryKeySelective(association);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }
}