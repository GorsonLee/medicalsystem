package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Doctor;
import com.starter.medicaldao.entity.dto.DoctorDto;
import com.starter.medicaldao.entity.filter.DoctorFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorMapper {
    int insert(Doctor record);

    int insertSelective(Doctor record);

    Doctor selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKey(Doctor record);

    Doctor selectByPhone(@Param("phone") String phone);

    Doctor selectByPhoneAndPwd(@Param("phone") String phone,
                               @Param("pwd") String pwd);

    List<Doctor> queryDoctors(Doctor doctor);

    List<Doctor> selectByHospital(@Param("hospital") String hospital);

    List<Doctor> selectByFilter(@Param("filter") DoctorFilter filter);
}