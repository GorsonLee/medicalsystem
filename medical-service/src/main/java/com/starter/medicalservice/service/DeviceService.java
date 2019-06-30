package com.starter.medicalservice.service;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.request.DeviceRequest;
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

    public BaseResponse insertSelective(DeviceRequest request) {
        Device oldOne = deviceMapper.selectByUserIdAndDeviceId(request.getUserId(), request.getDeviceId());

        if (oldOne != null) {
            return new BaseResponse<>(MsgCodeEnum.RESOURCE_EXIST_ERROR);
        }

        Date now = new Date();
        Device device = new Device();
        device.setId(UUIDUtil.getUUID());
        device.setUserId(request.getUserId());
        device.setDeviceId(request.getDeviceId());
        device.setCategory(request.getCategory());
        device.setType(request.getType());
        device.setState(request.getState());
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

    public BaseResponse update(DeviceRequest request) {
        Device device = new Device();
        device.setId(request.getId());
        device.setCategory(request.getCategory());
        device.setType(request.getType());
        device.setState(request.getState());
        device.setModifyTime(new Date());
        int result = deviceMapper.updateByPrimaryKeySelective(device);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }
}