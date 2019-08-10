package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.BodySignAlarm;
import com.starter.medicaldao.entity.Complaint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComplaintMapper {
    int insert(Complaint record);

    int insertSelective(Complaint record);

    Complaint selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Complaint record);

    int updateByPrimaryKey(Complaint record);

    List<Complaint> selectAll(@Param("offset") Integer offset,
                                  @Param("pageSize") Integer pageSize);
}