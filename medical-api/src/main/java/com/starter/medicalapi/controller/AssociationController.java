package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.request.AssociationRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Association;
import com.starter.medicalservice.service.AssociationService;
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
    public BaseResponse associate(@RequestBody AssociationRequest request) {
        return associationService.associate(request);
    }

    /**
     * 我关联的人
     *
     * @param userId
     * @return
     */
    @GetMapping("/listMyAssociateElder")
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
    public BaseResponse listAssociateMeUser(String userId) {
        return associationService.queryAssociateMeUser(userId);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody Association association) {
        return associationService.update(association);
    }
}