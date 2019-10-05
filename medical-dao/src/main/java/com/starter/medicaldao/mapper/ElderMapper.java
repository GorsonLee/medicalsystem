package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.entity.filter.ElderFilter;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ElderMapper {
    int insert(Elder record);

    int insertSelective(Elder record);

    Elder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Elder record);

    int updateByPrimaryKey(Elder record);

    Elder selectByPhone(@Param("phone") String phone);

    Elder selectByPhoneAndPwd(@Param("phone") String phone,
                              @Param("pwd") String pwd);

    Elder selectByPhoneAndNameAndIdentity(@Param("phone") String phone,
                                          @Param("name") String name,
                                          @Param("identity") String identity);

    int countByOrganizationId(@Param("agencyId") String agencyId);

    /**
     * 管理后台查询老人的信信
     *
     * @param provideState   养老状态
     * @param organizationId 机构ID
     * @param birth          生日
     * @param verifyState    审核状态
     * @param offset
     * @param pageSize
     * @return 老人列表
     */
    List<Elder> listElder(@Param("provideState") Integer provideState,
                          @Param("organizationId") String organizationId,
                          @Param("birth") Integer birth,
                          @Param("verifyState") Integer verifyState,
                          @Param("offset") Integer offset,
                          @Param("pageSize") Integer pageSize);

    int listCountElder(@Param("provideState") Integer provideState,
                          @Param("organizationId") String organizationId,
                          @Param("birth") Integer birth,
                          @Param("verifyState") Integer verifyState);

   // List<Elder> readByFilter(@Param("filter") ElderFilter filter);
}