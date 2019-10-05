package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Consult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsultMapper {
    int insert(Consult record);

    int insertSelective(Consult record);

    Consult selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Consult record);

    int updateByPrimaryKey(Consult record);

    List<Consult> selectByUserId(@Param("userId") String userId);

    List<Consult> selectByDoctorId(@Param("doctorId") String doctorId);
    List<Consult> selectAll(@Param("offset") Integer offset,
                            @Param("pageSize") Integer pageSize);

    int selectCount();
}