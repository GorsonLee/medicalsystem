package com.starter.medicalapi.controller.manage.basic;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.DateUtil;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.*;
import com.starter.medicaldao.entity.dto.ManagerDto;
import com.starter.medicaldao.entity.filter.ServiceCenterFilter;
import com.starter.medicaldao.mapper.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/manage/service")
@Api(tags = "Z管理后台#######服务管家")
public class ServiceController {

    @Resource
    private ManagerMapper managerMapper;
    @Resource
    private ServiceCenterMapper serviceCenterMapper;

    // 0-空闲 1-正在帮扶 2-正在服务 3-同时在帮扶与服务
    @GetMapping("/list")
    @ApiOperation("服务管家列表")
    public BaseResponse list(Integer offset,
                                    Integer pageSize) {
        log.info("/manage/service/list offset:{} pageSize:{}", offset, pageSize);
        BaseResponse<List<ManagerDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<ManagerDto> resultList= new ArrayList<>();

        List<Manager> managerList = managerMapper.selectByFilter(offset, pageSize);
        int count = managerMapper.selectCountByFilter();
        if (!CollectionUtils.isEmpty(managerList)) {
            managerList.stream().filter(manager -> manager != null).forEach(manager -> {
                ManagerDto managerDto = new ManagerDto();
                BeanUtils.copyProperties(manager, managerDto);
                if (manager.getCreateTime() != null) {
                    managerDto.setCreateTimeStr(DateUtil.dateToString(manager.getCreateTime()));
                }

                // 查询服务状态
                ServiceCenterFilter filter = new ServiceCenterFilter();
                filter.setHelpType(0);
                filter.setManagerId(manager.getId());
                filter.setStateList(Arrays.asList(1));
                List<ServiceCenter> serviceList = serviceCenterMapper.selectByFilter(filter);

                // 查询帮扶状态
                filter.setHelpType(1);
                List<ServiceCenter> helpList = serviceCenterMapper.selectByFilter(filter);

                if (CollectionUtils.isEmpty(serviceList) && CollectionUtils.isEmpty(helpList)) {
                    managerDto.setServiceState(0);
                }else if (!CollectionUtils.isEmpty(serviceList) && !CollectionUtils.isEmpty(helpList)) {
                    managerDto.setServiceState(3);
                }else if (!CollectionUtils.isEmpty(serviceList) && CollectionUtils.isEmpty(helpList)) {
                    managerDto.setServiceState(2);
                }else {
                    managerDto.setServiceState(1);
                }

                resultList.add(managerDto);
            });
        }
        response.setCount(count);
        response.setData(resultList);
        return response;
    }

    @PostMapping("/insert")
    @ApiOperation("添加服务人员")
    public BaseResponse create(@RequestBody Manager one) {
        log.info("/manage/service/insert one:{}", one);
        Date now = new Date();
        one.setId(UUIDUtil.getUUID());
        one.setCreateTime(now);
        one.setModifyTime(now);
        int result = managerMapper.insertSelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @PostMapping("/update")
    @ApiOperation("更新服务人员")
    public BaseResponse update(@RequestBody Manager one) {
        log.info("/manage/service/update one:{}", one);
        Date now = new Date();
        one.setModifyTime(now);
        int result = managerMapper.updateByPrimaryKeySelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @GetMapping("/detail")
    @ApiOperation("查看服务人员详情")
    public BaseResponse detail(String managerId) {
        log.info("/manage/service/detail managerId:{}", managerId);
        BaseResponse<ManagerDto> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);

        // 服务人员基本信息
        Manager manager = managerMapper.selectByPrimaryKey(managerId);
        ManagerDto dto = new ManagerDto();
        BeanUtils.copyProperties(manager, dto);
        if (manager.getCreateTime() != null) {
            dto.setCreateTimeStr(DateUtil.dateToString(manager.getCreateTime()));
        }

        // 服务订单个数 进行中和已完成状态
        List<Integer> stateList = Arrays.asList(1,2);
        ServiceCenterFilter filter = new ServiceCenterFilter();
        filter.setManagerId(managerId);
        filter.setHelpType(0);
        filter.setStateList(stateList);
        Integer serviceCount = serviceCenterMapper.selectServiceCountByFilter(filter);
        dto.setServiceCount(serviceCount);

        // 帮扶订单个数
        filter.setHelpType(1);
        Integer helpCount = serviceCenterMapper.selectServiceCountByFilter(filter);
        dto.setHelpCount(helpCount);

        // 平均评价星级，已完成并且有评价时间
        stateList = Arrays.asList(2);
        filter.setStateList(stateList);
        BigDecimal starCount = serviceCenterMapper.selectAvgStarCountByFilter(filter);
        dto.setStarCount(starCount);
        response.setData(dto);
        return response;
    }

    @PostMapping("/delete")
    @ApiOperation("删除服务人员")
    public BaseResponse delete(String id) {
        log.info("/manage/agency/delete agencyId:{}", id);
        int result = serviceCenterMapper.deleteByPrimaryKey(id);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }
}