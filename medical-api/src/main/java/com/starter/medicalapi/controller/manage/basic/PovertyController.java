package com.starter.medicalapi.controller.manage.basic;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.DateUtil;
import com.starter.medicalcommon.util.StringUtil;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.entity.Manager;
import com.starter.medicaldao.entity.ServiceCenter;
import com.starter.medicaldao.entity.dto.ManagerDto;
import com.starter.medicaldao.entity.dto.ServiceCenterDto;
import com.starter.medicaldao.entity.filter.ServiceCenterFilter;
import com.starter.medicaldao.entity.request.HelpRequest;
import com.starter.medicaldao.mapper.ElderMapper;
import com.starter.medicaldao.mapper.ManagerMapper;
import com.starter.medicaldao.mapper.ServiceCenterMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/manage/poverty")
@Api(tags = "帮扶订单")
public class PovertyController {

    @Resource
    private ManagerMapper managerMapper;
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private ServiceCenterMapper serviceCenterMapper;

    @GetMapping("/list")
    @ApiOperation("帮扶订单列表")
    public BaseResponse list(Integer state, Integer offset,
                             Integer pageSize) {
        BaseResponse<List<ServiceCenterDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<ServiceCenterDto> resultList= new ArrayList<>();

        ServiceCenterFilter filter = new ServiceCenterFilter();
        filter.setStateList(Arrays.asList(state));
        filter.setPageSize(pageSize);
        filter.setOffset(offset);
        filter.setHelpType(1);
        List<ServiceCenter> serviceCenterList = serviceCenterMapper.selectByFilter(filter);
        if (!CollectionUtils.isEmpty(serviceCenterList)) {
            serviceCenterList.stream().filter(serviceCenter -> serviceCenter != null).forEach(serviceCenter -> {
                ServiceCenterDto dto = new ServiceCenterDto();
                BeanUtils.copyProperties(serviceCenter, dto);
                if (serviceCenter.getCreateTime() != null) {
                    dto.setCreateTimeStr(DateUtil.dateToString(serviceCenter.getCreateTime()));
                }
                if (serviceCenter.getServiceTime() != null) {
                    dto.setServiceTimeStr(DateUtil.dateToString(serviceCenter.getServiceTime()));
                }
                // 老人的电话和姓名
                Elder elder = elderMapper.selectByPrimaryKey(dto.getUserId());
                if (Objects.nonNull(elder)) {
                    dto.setUserPhone(elder.getPhone());
                    dto.setUserName(elder.getName());
                }
                // 服务人员的电话和姓名
                Manager manager = managerMapper.selectByPrimaryKey(dto.getManagerId());
                if (Objects.nonNull(manager)) {
                    dto.setManagerPhone(manager.getPhone());
                    dto.setManagerName(manager.getName());
                }
                resultList.add(dto);
            });
        }
        response.setData(resultList);
        return response;
    }

    @PostMapping("/insert")
    @ApiOperation("添加帮扶订单")
    public BaseResponse create(@RequestBody HelpRequest request) {
        Date now = new Date();

        ServiceCenter one = new ServiceCenter();
        one.setId(UUIDUtil.getUUID());
        one.setHelpType(0);
        one.setState(0);
        one.setRequirement(request.getRequirement());
        if (!StringUtils.isEmpty(request.getServiceTime())) {
            one.setServiceTime(DateUtil.stringToDate(request.getServiceTime()));
        }
        one.setCreateTime(now);
        one.setModifyTime(now);
        // 老人ID
        Elder elder = elderMapper.selectByPhone(request.getUserPhone());
        if (elder == null) {
            return new BaseResponse(MsgCodeEnum.ELDER_NOT_EXISTS);
        }
        one.setUserId(elder.getId());
        // 服务人员ID
        Manager manager = managerMapper.selectByPhone(request.getUserPhone());
        if (manager == null) {
            return new BaseResponse(MsgCodeEnum.MANAGER_NOT_EXISTS);
        }
        one.setManagerId(manager.getId());

        int result = serviceCenterMapper.insertSelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @GetMapping("/detail")
    @ApiOperation("查看帮扶订单详情")
    public BaseResponse detail(String helpId) {
        BaseResponse<ServiceCenterDto> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        ServiceCenterDto dto = new ServiceCenterDto();

        ServiceCenter serviceCenter = serviceCenterMapper.selectByPrimaryKey(helpId);
        BeanUtils.copyProperties(serviceCenter, dto);

        if (Objects.nonNull(dto)) {
            if (serviceCenter.getCreateTime() != null) {
                dto.setCreateTimeStr(DateUtil.dateToString(serviceCenter.getCreateTime()));
            }
            if (serviceCenter.getServiceTime() != null) {
                dto.setServiceTimeStr(DateUtil.dateToString(serviceCenter.getServiceTime()));
            }
            if (serviceCenter.getFinishTime() != null) {
                dto.setFinishTimeStr(DateUtil.dateToString(serviceCenter.getFinishTime()));
            }
            if (serviceCenter.getEvaluateTime() != null) {
                dto.setEvaluateTimeStr(DateUtil.dateToString(serviceCenter.getEvaluateTime()));
            }

            // 老人的电话和姓名
            Elder elder = elderMapper.selectByPrimaryKey(dto.getUserId());
            if (Objects.nonNull(elder)) {
                dto.setUserPhone(elder.getPhone());
                dto.setUserName(elder.getName());
            }
            // 服务人员的电话和姓名
            Manager manager = managerMapper.selectByPrimaryKey(dto.getManagerId());
            if (Objects.nonNull(manager)) {
                dto.setManagerPhone(manager.getPhone());
                dto.setManagerName(manager.getName());
            }
        }
        response.setData(dto);
        return response;
    }

}