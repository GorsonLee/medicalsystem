package com.starter.medicalapi.controller;

import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.BodySign;
import com.starter.medicalservice.service.BodySignService;
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
@RequestMapping("/bodySign")
@Api(tags = "体征数据")
public class BodySignController {

    @Resource
    private BodySignService bodySignService;

    @PostMapping("/insert")
    @ApiOperation("新增")
    public BaseResponse insert(@RequestBody BodySign bodySign) {
        return bodySignService.insert(bodySign);
    }

    @GetMapping("/history")
    @ApiOperation("历史体征")
    public BaseResponse history(@NotNull String userId,
                                @NotNull String bodySign,
                                @NotNull Integer offset,
                                @NotNull Integer pageSize) {
        return bodySignService.queryByUserIdAndBodySign(userId, bodySign, offset, pageSize);
    }

    @GetMapping("/overView")
    @ApiOperation("预览体征")
    public BaseResponse overView(@NotNull String userId) {
        return bodySignService.queryBodySignOverView(userId);
    }
}