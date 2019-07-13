package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.request.AssociationRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Association;
import com.starter.medicalservice.service.AssociationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/association")
@Api(tags = "关联老人")
public class AssociationController {

    @Resource
    private AssociationService associationService;

    /**
     * 关联用户
     *
     * @param request
     * @return
     */
    @PostMapping("/associate")
    @ApiOperation("关联老人")
    public BaseResponse associate(@RequestBody AssociationRequest request) {
        return associationService.associate(request);
    }

    @PostMapping("/update")
    @ApiOperation("更新关联的老人")
    public BaseResponse update(@RequestBody Association association) {
        return associationService.update(association);
    }

    /**
     * 我关联的人
     *
     * @param userId
     * @return
     */
    @GetMapping("/listMyAssociateElder")
    @ApiOperation("我关联的老人")
    public BaseResponse listMyAssociateElder(String userId) {
        return associationService.queryMyAssociateElder(userId);
    }

    /**
     * 关联我的人
     *
     * @param userId
     * @return
     */
    @GetMapping("/listAssociateMeUser")
    @ApiOperation("关联我的人")
    public BaseResponse listAssociateMeUser(String userId) {
        return associationService.queryAssociateMeUser(userId);
    }
}