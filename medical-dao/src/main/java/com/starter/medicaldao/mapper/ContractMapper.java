package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Contract;

public interface ContractMapper {
    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);
}