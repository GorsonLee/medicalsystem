package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Younger;
import com.starter.medicaldao.entity.filter.ElderFilter;
import com.starter.medicaldao.entity.filter.YoungerFilter;
import com.starter.medicaldao.entity.vo.ElderRelationContractVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YoungerMapper {
    int insert(Younger record);

    int insertSelective(Younger record);

    Younger selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Younger record);

    int updateByPrimaryKey(Younger record);

    Younger selectByPhone(@Param("phone") String phone);

    Younger selectByPhoneAndPwd(@Param("phone") String phone,
                                @Param("pwd") String pwd);

    List<Younger> readList( @Param("offset") Integer offset,
                            @Param("pageSize") Integer pageSize);

    int selectCount();
}