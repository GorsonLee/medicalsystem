package com.starter.medicalapi.controller.manage.security;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.DateUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.BodySignAlarm;
import com.starter.medicaldao.entity.Device;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.entity.dto.BodySignAlarmDto;
import com.starter.medicaldao.entity.dto.DeviceDto;
import com.starter.medicaldao.mapper.BodySignAlarmMapper;
import com.starter.medicaldao.mapper.DeviceMapper;
import com.starter.medicaldao.mapper.DoctorMapper;
import com.starter.medicaldao.mapper.ElderMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/manage/alarm")
@Api(tags = "健康预警")
public class AlarmController {

    @Resource
    private BodySignAlarmMapper bodySignAlarmMapper;
    @Resource
    private DoctorMapper doctorMapper;

    @GetMapping("/list")
    @ApiOperation("预警列表")
    public BaseResponse list(String type, Integer offset, Integer pageSize) {
        BaseResponse<List<BodySignAlarmDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<BodySignAlarmDto> resultList= new ArrayList<>();

        List<BodySignAlarm> alarmList = bodySignAlarmMapper.selectAll(offset, pageSize);
        if (!CollectionUtils.isEmpty(alarmList)) {
            alarmList.stream().filter(alarm -> alarm != null).forEach(alarm -> {
                BodySignAlarmDto dto = new BodySignAlarmDto();
                BeanUtils.copyProperties(alarm, dto);
                if (alarm.getCreateTime() != null) {
                    dto.setCreateTimeStr(DateUtil.dateToString(alarm.getCreateTime()));
                }

                // 医生的电话和姓名
                Doctor doctor = doctorMapper.selectByPrimaryKey(dto.getDoctorId());
                if (Objects.nonNull(doctor)) {
                    dto.setDoctorPhone(doctor.getPhone());
                    dto.setDoctorName(doctor.getName());
                }
                resultList.add(dto);
            });
        }
        response.setData(resultList);
        return response;
    }
}