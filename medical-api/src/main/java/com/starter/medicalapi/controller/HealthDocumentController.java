package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.request.HealthDocumentRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicalservice.service.HealthDocumentService;
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
public class HealthDocumentController {

    @Resource
    private HealthDocumentService healthDocumentService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody HealthDocumentRequest request) {
        return healthDocumentService.insertSelective(request);
    }

    /**
     * 根据用户ID和档案路径查找
     *
     * @param userId 用户ID
     * @param path   健康档案路径
     * @return 用户健康档案信息
     */
    @GetMapping("/selectByUserIdAndPath")
    public BaseResponse selectByUserIdAndPath(@NotNull String userId, @NotNull String path) {
        return healthDocumentService.selectByUserIdAndPath(userId, path);
    }

    /**
     * 根据用户ID和档案路径更新健康档案
     *
     * @param request 档案内容
     * @return true更新成功，false更新异常
     */
    @PostMapping("/updateByUserIdAndPath")
    public BaseResponse updateByUserIdAndPath(@RequestBody HealthDocumentRequest request) {
        return healthDocumentService.updateByUserIdAndPathSelective(request);
    }
}