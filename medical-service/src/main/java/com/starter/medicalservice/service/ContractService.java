package com.starter.medicalservice.service;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Contract;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicaldao.mapper.ContractMapper;
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
public class ContractService {

    @Resource
    private ContractMapper contractMapper;
    @Resource
    private DoctorMapper doctorMapper;

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
}
