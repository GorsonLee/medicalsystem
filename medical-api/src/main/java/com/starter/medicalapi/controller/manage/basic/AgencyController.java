package com.starter.medicalapi.controller.manage.basic;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Agency;
import com.starter.medicaldao.mapper.AgencyMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-07-14 1:00
 **/
@RestController
@RequestMapping("/manage/agency")
@Api(tags = "机构")
public class AgencyController {

    @Resource
    private AgencyMapper agencyMapper;

    @PostMapping("/insert")
    @ApiOperation("新增")
    public BaseResponse insert(@RequestBody Agency one) {
        Date now = new Date();
        one.setId(UUIDUtil.getUUID());
        one.setCreateTime(now);
        one.setModifyTime(now);
        int result = agencyMapper.insertSelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @PostMapping("/update")
    @ApiOperation("更新")
    public BaseResponse update(@RequestBody Agency one) {
        Date now = new Date();
        one.setModifyTime(now);
        int result = agencyMapper.updateByPrimaryKeySelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @GetMapping("/list")
    @ApiOperation("列表")
    public BaseResponse list(String province,
                             String city,
                             String name,
                             Integer offset,
                             Integer pageSize) {
        List<Agency> agencyList = agencyMapper.selectByParams(province, city, name, offset, pageSize);

        //TODO 统计机构的服务人数
        BaseResponse<List<Agency>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(agencyList);
        return response;
    }
}
