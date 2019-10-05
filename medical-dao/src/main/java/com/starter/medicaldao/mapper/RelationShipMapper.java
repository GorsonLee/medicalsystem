package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.RelationShip;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelationShipMapper {
    int insert(RelationShip record);

    int insertSelective(RelationShip record);

    RelationShip selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RelationShip record);

    int updateByPrimaryKey(RelationShip record);

    List<RelationShip> selectByElderId(@Param("elderId") String elderId);
}