package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Remind;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RemindMapper {
    int insert(Remind record);

    int insertSelective(Remind record);

    Remind selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Remind record);

    int updateByPrimaryKey(Remind record);

    List<Remind> selectByUserId(@Param("userId") String userId);
}