package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Contract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractMapper {
    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);

    List<Contract> selectByUserId(@Param("userId") String userId);

    List<Contract> selectByDoctorId(@Param("doctorId") String doctorId);

}