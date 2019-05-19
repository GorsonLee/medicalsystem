package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.RelationShip;

public interface RelationShipMapper {
    int insert(RelationShip record);

    int insertSelective(RelationShip record);

    RelationShip selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RelationShip record);

    int updateByPrimaryKey(RelationShip record);
}