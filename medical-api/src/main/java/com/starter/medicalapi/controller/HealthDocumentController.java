package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.HealthDocument;
import com.starter.medicalservice.service.HealthDocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/healthDocument")
@Api(tags = "健康档案")
public class HealthDocumentController {

    @Resource
    private HealthDocumentService healthDocumentService;

    @PostMapping("/insert")
    @ApiOperation("新增")
    public BaseResponse insert(@RequestBody HealthDocument healthDocument) {
        return healthDocumentService.insert(healthDocument);
    }

    /**
     * 根据用户ID和档案路径查找
     *
     * @param userId 用户ID
     * @param path   健康档案路径
     * @return 用户健康档案信息
     */
    @GetMapping("/queryByUserIdAndPath")
    @ApiOperation("根据用户ID和档案路径查找")
    public BaseResponse queryByUserIdAndPath(@NotNull String userId, @NotNull String path) {
        return healthDocumentService.queryByUserIdAndPath(userId, path);
    }

    /**
     * 根据用户ID和档案路径更新健康档案
     *
     * @param healthDocument 档案内容
     * @return true更新成功，false更新异常
     */
    @PostMapping("/updateByUserIdAndPath")
    @ApiOperation("根据用户ID和档案路径更新健康档案")
    public BaseResponse updateByUserIdAndPath(@RequestBody HealthDocument healthDocument) {
        return healthDocumentService.updateByUserIdAndPathSelective(healthDocument);
    }
}