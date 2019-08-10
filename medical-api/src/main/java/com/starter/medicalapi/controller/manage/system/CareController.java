package com.starter.medicalapi.controller.manage.system;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.DateUtil;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Care;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicaldao.entity.Hospital;
import com.starter.medicaldao.entity.dto.CareDto;
import com.starter.medicaldao.entity.dto.HospitalDto;
import com.starter.medicaldao.mapper.CareMapper;
import com.starter.medicaldao.mapper.ContractMapper;
import com.starter.medicaldao.mapper.DoctorMapper;
import com.starter.medicaldao.mapper.HospitalMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/manage/care")
@Api(tags = "关怀")
public class CareController {

    @Resource
    private CareMapper careMapper;

    @GetMapping("/list")
    @ApiOperation("关怀列表")
    public BaseResponse list(Integer offset,
                                  Integer pageSize) {
        BaseResponse<List<CareDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<Care> careList = careMapper.selectAll(offset, pageSize);
        List<CareDto> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(careList)) {
            careList.stream().filter(care -> care != null).forEach(care -> {
                CareDto dto = new CareDto();
                BeanUtils.copyProperties(care, dto);
                if (care.getCreateTime() != null) {
                    dto.setCreateTimeStr(DateUtil.dateToString(care.getCreateTime()));
                }
                resultList.add(dto);
            });
        }
        response.setData(resultList);
        return response;
    }

    @PostMapping("/insert")
    @ApiOperation("新增关怀模板")
    public BaseResponse create(@RequestBody Care one) {
        Date now = new Date();
        one.setId(UUIDUtil.getUUID());
        one.setCreateTime(now);
        one.setModifyTime(now);
        int result = careMapper.insertSelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @PostMapping("/update")
    @ApiOperation("更新关怀模板")
    public BaseResponse update(@RequestBody Care one) {
        Date now = new Date();
        one.setModifyTime(now);
        int result = careMapper.updateByPrimaryKeySelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @GetMapping("/detail")
    @ApiOperation("查看关怀模板详情")
    public BaseResponse detail(String careId) {
        Care care = careMapper.selectByPrimaryKey(careId);

        CareDto dto = new CareDto();
        BeanUtils.copyProperties(care, dto);
        if (care.getCreateTime() != null) {
            dto.setCreateTimeStr(DateUtil.dateToString(care.getCreateTime()));
        }
        BaseResponse<CareDto> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(dto);
        return response;
    }

    @PostMapping("/delete")
    @ApiOperation("删除关怀模板")
    public BaseResponse update(String careId) {
        int result = careMapper.deleteByPrimaryKey(careId);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

}