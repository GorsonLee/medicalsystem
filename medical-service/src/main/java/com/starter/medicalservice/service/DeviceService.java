package com.starter.medicalservice.service;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Device;
import com.starter.medicaldao.mapper.DeviceMapper;
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
public class DeviceService {

    @Resource
    private DeviceMapper deviceMapper;

    public BaseResponse insert(Device device) {
        Device oldOne = deviceMapper.selectByUserIdAndDeviceId(device.getUserId(), device.getDeviceId());

        if (oldOne != null) {
            return new BaseResponse<>(MsgCodeEnum.RESOURCE_EXIST_ERROR);
        }

        Date now = new Date();
        device.setId(UUIDUtil.getUUID());
        device.setCreateTime(now);
        device.setModifyTime(now);
        int result = deviceMapper.insertSelective(device);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    public BaseResponse<List<Device>> queryByUserId(String userId) {
        List<Device> deviceList = deviceMapper.selectByUserId(userId);
        BaseResponse<List<Device>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(deviceList);
        return response;
    }

    public BaseResponse update(Device device) {
        device.setModifyTime(new Date());
        int result = deviceMapper.updateByPrimaryKeySelective(device);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }
}