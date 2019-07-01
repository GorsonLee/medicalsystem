package com.starter.medicalservice.service;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Remind;
import com.starter.medicaldao.mapper.RemindMapper;
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
public class RemindService {

    @Resource
    private RemindMapper remindMapper;

    public BaseResponse insert(Remind remind) {
        Date now = new Date();
        remind.setId(UUIDUtil.getUUID());
        remind.setCreateTime(now);
        remind.setModifyTime(now);
        int result = remindMapper.insertSelective(remind);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse<List<Remind>> queryByUserId(String userId) {
        List<Remind> remindList = remindMapper.selectByUserId(userId);
        BaseResponse<List<Remind>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(remindList);
        return response;
    }

    public BaseResponse update(Remind remind) {
        remind.setModifyTime(new Date());
        int result = remindMapper.updateByPrimaryKeySelective(remind);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }
}