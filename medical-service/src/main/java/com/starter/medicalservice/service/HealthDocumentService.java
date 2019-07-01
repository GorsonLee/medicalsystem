package com.starter.medicalservice.service;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.HealthDocument;
import com.starter.medicaldao.mapper.HealthDocumentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@Service
@Slf4j
public class HealthDocumentService {

    @Resource
    private HealthDocumentMapper healthDocumentMapper;

    public BaseResponse insert(HealthDocument healthDocument) {
        HealthDocument oldOne = healthDocumentMapper.selectByUserIdAndPath(healthDocument.getUserId(), healthDocument.getPath());

        if (oldOne != null) {
            return new BaseResponse<>(MsgCodeEnum.RESOURCE_EXIST_ERROR);
        }

        Date now = new Date();
        healthDocument.setCreateTime(now);
        healthDocument.setModifyTime(now);
        int result = healthDocumentMapper.insertSelective(healthDocument);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    /**
     * 根据用户ID和档案路径查找
     *
     * @param userId 用户ID
     * @param path   健康档案路径
     * @return 用户健康档案信息
     */
    public BaseResponse<HealthDocument> queryByUserIdAndPath(String userId, String path) {
        HealthDocument healthDocument = healthDocumentMapper.selectByUserIdAndPath(userId, path);
        BaseResponse<HealthDocument> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(healthDocument);
        return response;
    }

    /**
     * 根据用户ID和档案路径更新健康档案
     *
     * @param healthDocument
     * @return true更新成功，false更新异常
     */
    public BaseResponse updateByUserIdAndPathSelective(HealthDocument healthDocument) {
        healthDocument.setModifyTime(new Date());
        int result = healthDocumentMapper.updateByPrimaryKeySelective(healthDocument);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }
}