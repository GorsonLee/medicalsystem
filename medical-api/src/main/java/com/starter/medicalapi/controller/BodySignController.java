package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.request.BodySignInsertRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicalservice.service.BodySignService;
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
@RequestMapping("/bodySign")
public class BodySignController {

    @Resource
    private BodySignService bodySignService;

    @PostMapping("/insert")
    public BaseResponse insert(@RequestBody BodySignInsertRequest request) {
        return bodySignService.insertSelective(request);
    }

    @GetMapping("/history")
    public BaseResponse history(@NotNull String userId,
                                @NotNull String bodySign,
                                @NotNull Integer offset,
                                @NotNull Integer pageSize) {
        return bodySignService.queryByUserIdAndBodySign(userId, bodySign, offset, pageSize);
    }

    @GetMapping("/overView")
    public BaseResponse overView(@NotNull String userId) {
        return bodySignService.queryBodySignOverView(userId);
    }
}