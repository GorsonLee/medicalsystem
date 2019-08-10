package com.starter.medicalapi.controller.manage.security;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.DateUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Device;
import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.entity.Manager;
import com.starter.medicaldao.entity.ServiceCenter;
import com.starter.medicaldao.entity.dto.DeviceDto;
import com.starter.medicaldao.entity.dto.ServiceCenterDto;
import com.starter.medicaldao.entity.filter.ServiceCenterFilter;
import com.starter.medicaldao.mapper.DeviceMapper;
import com.starter.medicaldao.mapper.ElderMapper;
import com.starter.medicaldao.mapper.ManagerMapper;
import com.starter.medicaldao.mapper.ServiceCenterMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/manage/device")
@Api(tags = "设备管理")
public class DeviceManageController {

    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private ElderMapper elderMapper;

    @GetMapping("/list")
    @ApiOperation("设备列表")
    public BaseResponse list(String type, Integer offset, Integer pageSize) {
        BaseResponse<List<DeviceDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<DeviceDto> resultList= new ArrayList<>();

        List<Device> deviceList = deviceMapper.selectByType(type, offset, pageSize);
        if (!CollectionUtils.isEmpty(deviceList)) {
            deviceList.stream().filter(device -> device != null).forEach(device -> {
                DeviceDto dto = new DeviceDto();
                BeanUtils.copyProperties(device, dto);
                if (device.getCreateTime() != null) {
                    dto.setCreateTimeStr(DateUtil.dateToString(device.getCreateTime()));
                }

                // 老人的电话和姓名
                Elder elder = elderMapper.selectByPrimaryKey(dto.getUserId());
                if (Objects.nonNull(elder)) {
                    dto.setUserPhone(elder.getPhone());
                    dto.setUserName(elder.getName());
                }
                resultList.add(dto);
            });
        }
        response.setData(resultList);
        return response;
    }
}