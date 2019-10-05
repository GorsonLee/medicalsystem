package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.BodySign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BodySignMapper {
    int insert(BodySign record);

    int insertSelective(BodySign record);

    BodySign selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BodySign record);

    int updateByPrimaryKey(BodySign record);

    List<BodySign> selectByUserIdAndBodySign(@Param("userId") String userId,
                                             @Param("bodySign") String bodySign,
                                             @Param("offset") Integer offset,
                                             @Param("pageSize") Integer pageSize);

    List<BodySign> selectByUserId(@Param("userId") String userId);
}