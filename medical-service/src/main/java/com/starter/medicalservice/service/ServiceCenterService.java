package com.starter.medicalservice.service;

import com.alibaba.fastjson.JSON;
import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.entity.Manager;
import com.starter.medicaldao.entity.Revisit;
import com.starter.medicaldao.entity.ServiceCenter;
import com.starter.medicaldao.mapper.ElderMapper;
import com.starter.medicaldao.mapper.ManagerMapper;
import com.starter.medicaldao.mapper.RevisitMapper;
import com.starter.medicaldao.mapper.ServiceCenterMapper;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
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
public class ServiceCenterService {

    @Resource
    private ServiceCenterMapper serviceCenterMapper;
    @Resource
    private ManagerMapper managerMapper;
    @Resource
    private ElderMapper elderMapper;

    public BaseResponse insert(ServiceCenter serviceCenter) {
        Date now = new Date();
        serviceCenter.setId(UUIDUtil.getUUID());
        serviceCenter.setCreateTime(now);
        serviceCenter.setModifyTime(now);
        int result = serviceCenterMapper.insertSelective(serviceCenter);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse update(ServiceCenter serviceCenter) {
        serviceCenter.setModifyTime(new Date());
        int result = serviceCenterMapper.updateByPrimaryKeySelective(serviceCenter);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse<List<ServiceCenter>> queryByState(Integer state) {
        List<ServiceCenter> serviceCenterList = serviceCenterMapper.selectBydState(state);
        BaseResponse<List<ServiceCenter>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(serviceCenterList);
        return response;
    }

    public BaseResponse<List<JSONObject>> queryByUserIdAndState(String userId, Integer state) {
        List<ServiceCenter> serviceCenterList = serviceCenterMapper.selectByUserIdAndState(userId, state);
        BaseResponse<List<JSONObject>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);

        List<JSONObject> jsonList = serviceCenterList.stream()
                .map(item -> {
                   JSONObject json = (JSONObject) JSON.toJSON(item);
                    json.put("name", "");

                   if (StringUtils.isNotEmpty(item.getManagerId())) {
                       Manager manager = managerMapper.selectByPrimaryKey(item.getManagerId());
                       if (manager != null) {
                           json.put("name", manager.getName());
                       }
                   }

                   return json;
                }).collect(Collectors.toList());

        response.setData(jsonList);
        return response;
    }

    public BaseResponse<List<JSONObject>> queryByManagerIdAndState(String managerId, Integer state) {
        List<ServiceCenter> serviceCenterList = serviceCenterMapper.selectByManagerIdAndState(managerId, state);
        BaseResponse<List<JSONObject>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);

        List<JSONObject> jsonList = serviceCenterList.stream()
                .map(item -> {
                    JSONObject json = (JSONObject) JSON.toJSON(item);
                    json.put("name", "");

                    if (StringUtils.isNotEmpty(item.getManagerId())) {
                        Elder elder = elderMapper.selectByPrimaryKey(item.getUserId());

                        if (elder != null) {
                            json.put("name", elder.getName());
                        }
                    }

                    return json;
                }).collect(Collectors.toList());

        response.setData(jsonList);
        return response;
    }
}