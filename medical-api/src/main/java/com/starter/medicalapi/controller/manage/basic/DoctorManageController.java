package com.starter.medicalapi.controller.manage.basic;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.DateUtil;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicaldao.entity.Hospital;
import com.starter.medicaldao.entity.dto.DoctorDto;
import com.starter.medicaldao.entity.dto.HospitalDto;
import com.starter.medicaldao.entity.filter.DoctorFilter;
import com.starter.medicaldao.mapper.ContractMapper;
import com.starter.medicaldao.mapper.DoctorMapper;
import com.starter.medicaldao.mapper.HospitalMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.print.Doc;
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
@RequestMapping("/manage/doctor")
@Api(tags = "医生管理")
public class DoctorManageController {
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private ContractMapper contractMapper;

    @GetMapping("/list")
    @ApiOperation("医生列表")
    public BaseResponse list(String province,
                             String city,
                             String country,
                             String town,
                             String community,
                             String hospitalName,
                             String department,
                             String secondDepartment,
                             Integer offset,
                             Integer pageSize) {
        BaseResponse<List<DoctorDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<DoctorDto> resultList = new ArrayList<>();

        DoctorFilter filter = new DoctorFilter();
        filter.setHospitalName(hospitalName);
        filter.setDepartment(department);
        filter.setSecondDepartment(secondDepartment);
        filter.setProvince(province);
        filter.setCity(city);
        filter.setCountry(country);
        filter.setTown(town);
        filter.setCommunity(community);
        filter.setOffset(offset);
        filter.setPageSize(pageSize);
        List<Doctor> doctorList = doctorMapper.selectByFilter(filter);

        // 签约老人个数
        if (!CollectionUtils.isEmpty(doctorList)) {
            doctorList.stream().filter(doctor -> doctor != null).forEach(doctor -> {
                List<String> doctorIdList = new ArrayList<>();
                doctorIdList.add(doctor.getId());
                Integer elderCount = contractMapper.selectCountByDoctorIdList(doctorIdList);

                DoctorDto doctorDto = new DoctorDto();
                BeanUtils.copyProperties(doctor, doctorDto);
                if (doctor.getCreateTime() != null) {
                    doctorDto.setCreateTimeStr(DateUtil.dateToString(doctor.getCreateTime()));
                }
                doctorDto.setElderCount(elderCount);
                resultList.add(doctorDto);
            });

        }
        response.setData(resultList);
        return response;
    }

    @PostMapping("/insert")
    @ApiOperation("添加医生")
    public BaseResponse create(@RequestBody Doctor one) {
        Date now = new Date();
        one.setId(UUIDUtil.getUUID());
        one.setCreateTime(now);
        one.setModifyTime(now);
        int result = doctorMapper.insertSelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @PostMapping("/update")
    @ApiOperation("更新医生")
    public BaseResponse update(@RequestBody Doctor one) {
        Date now = new Date();
        one.setModifyTime(now);
        int result = doctorMapper.updateByPrimaryKeySelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @GetMapping("/detail")
    @ApiOperation("查看医生详情")
    public BaseResponse detail(String doctorId) {
        Doctor doctor = doctorMapper.selectByPrimaryKey(doctorId);
        BaseResponse<Doctor> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(doctor);
        return response;
    }
}