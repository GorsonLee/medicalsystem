package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.BodySignAlarm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BodySignAlarmMapper {
    int insert(BodySignAlarm record);

    int insertSelective(BodySignAlarm record);

    BodySignAlarm selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BodySignAlarm record);

    int updateByPrimaryKey(BodySignAlarm record);

    List<BodySignAlarm> selectByDoctorId(@Param("doctorId") String doctorId);

    List<BodySignAlarm> selectAll(@Param("offset") Integer offset,
                                  @Param("pageSize") Integer pageSize);

}