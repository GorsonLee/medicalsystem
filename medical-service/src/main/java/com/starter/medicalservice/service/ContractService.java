package com.starter.medicalservice.service;

import com.alibaba.fastjson.JSONObject;
import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Contract;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.mapper.ContractMapper;
import com.starter.medicaldao.mapper.DoctorMapper;
import com.starter.medicaldao.mapper.ElderMapper;
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
public class ContractService {

    @Resource
    private ContractMapper contractMapper;
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private ElderMapper elderMapper;

    public BaseResponse insert(Contract contract) {
        Date now = new Date();
        contract.setId(UUIDUtil.getUUID());
        contract.setState(0);
        contract.setCreateTime(now);
        contract.setModifyTime(now);
        int result = contractMapper.insertSelective(contract);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse queryContractDoctors(String userId) {
        List<Contract> contractList = contractMapper.selectByUserId(userId);
        BaseResponse<List<Doctor>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(contractList.stream()
                .map(item -> doctorMapper.selectByPrimaryKey(item.getDoctorId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        return response;
    }

    public BaseResponse queryContractPatients(String doctorId) {
        List<Contract> contractList = contractMapper.selectByUserId(doctorId);
        BaseResponse<List<JSONObject>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(contractList.stream()
                .map(item -> {
                    Elder elder = elderMapper.selectByPrimaryKey(item.getUserId());
                    JSONObject result = new JSONObject();
                    result.put("id", item.getId());
                    result.put("userId", item.getUserId());
                    result.put("doctorId", item.getDoctorId());
                    result.put("phone", elder.getPhone());
                    result.put("name", elder.getName());
                    result.put("image", elder.getImage());
                    result.put("sex", elder.getSex());
                    result.put("createTime", elder.getCreateTime());
                    result.put("state", item.getState());
                    return result;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        return response;
    }
}
