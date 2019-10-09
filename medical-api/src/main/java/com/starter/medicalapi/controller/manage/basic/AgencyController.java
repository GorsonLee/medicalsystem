package com.starter.medicalapi.controller.manage.basic;

import com.alibaba.fastjson.JSON;
import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.DateUtil;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Agency;
import com.starter.medicaldao.entity.Care;
import com.starter.medicaldao.entity.dto.AgencyDto;
import com.starter.medicaldao.entity.dto.CareDto;
import com.starter.medicaldao.mapper.AgencyMapper;
import com.starter.medicaldao.mapper.ElderMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-07-14 1:00
 **/
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/manage/agency")
@Api(tags = "Z管理后台#######机构管理")
public class AgencyController {

    @Resource
    private AgencyMapper agencyMapper;
    @Resource
    private ElderMapper elderMapper;

    @PostMapping("/insert")
    @ApiOperation("新增")
    public BaseResponse insert(@RequestBody @Valid Agency one) {
        log.info("/manage/agency/insert request:{}", one);
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
        log.info("/manage/agency/update request:{}", one);
        Date now = new Date();
        one.setModifyTime(now);
        int result = agencyMapper.updateByPrimaryKeySelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @GetMapping("/detail")
    @ApiOperation("查看机构详情")
    public BaseResponse detail(String agencyId) {
        log.info("/manage/agency/detail agencyId:{}", agencyId);
        Agency agency = agencyMapper.selectByPrimaryKey(agencyId);

        AgencyDto dto = new AgencyDto();
        BeanUtils.copyProperties(agency, dto);
        if (agency.getCreateTime() != null) {
            dto.setCreateTimeStr(DateUtil.dateToString(agency.getCreateTime()));
        }
        BaseResponse<AgencyDto> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(dto);
        return response;
    }

    @GetMapping("/list")
    @ApiOperation("列表")
    public BaseResponse list(String province,
                             String city,
                             String agencyType,
                             Integer offset,
                             Integer pageSize) {
        log.info("/manage/agency/list province:{} city:{} agencyType:{} offset:{} pageSize:{}", province, city, agencyType, offset, pageSize);
        List<Agency> agencyList = agencyMapper.selectByParamss(province, city, agencyType, offset, pageSize);
        Integer count = agencyMapper.selectCountByParamss(province, city, agencyType);
        BaseResponse<List<AgencyDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<AgencyDto> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(agencyList)) {
            agencyList.stream().filter(agency -> agency != null).forEach(agency -> {
                AgencyDto dto = new AgencyDto();
                Integer elderCount = elderMapper.countByOrganizationId(agency.getId());
                BeanUtils.copyProperties(agency, dto);
                if (agency.getCreateTime() != null) {
                    dto.setCreateTimeStr(DateUtil.dateToString(agency.getCreateTime()));
                }
                dto.setElderCount(elderCount);
                resultList.add(dto);
            });

        }
        response.setCount(count);
        response.setData(resultList);
        return response;
    }

    @PostMapping("/delete")
    @ApiOperation("删除机构")
    public BaseResponse delete(String agencyId) {
        log.info("/manage/agency/delete agencyId:{}", agencyId);
        int result = agencyMapper.deleteByPrimaryKey(agencyId);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @GetMapping("/agencyTypeList")
    @ApiOperation("列表")
    public BaseResponse getAgencyTypeList() {
        List<Agency> agencyList = agencyMapper.selectAgencyTypeList();
        List<String> agencyTypeList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(agencyList)) {
            agencyList.stream().filter(agency -> agency != null).forEach(agency -> {
                agencyTypeList.add(agency.getType());
            });

        }
        BaseResponse<List<String>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(agencyTypeList);
        return response;
    }

    @GetMapping("/agencyIdList")
    @ApiOperation("列表")
    public BaseResponse getAgencyIdList() {
        List<Agency> agencyList = agencyMapper.selectAgencyIdList();
        List<String> agencyIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(agencyList)) {
            agencyList.stream().filter(agency -> agency != null).forEach(agency -> {
                agencyIdList.add(agency.getId());
            });

        }
        BaseResponse<List<String>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(agencyIdList);
        return response;
    }
}
