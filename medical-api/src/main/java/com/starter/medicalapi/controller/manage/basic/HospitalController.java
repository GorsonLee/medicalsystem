package com.starter.medicalapi.controller.manage.basic;

import com.alibaba.fastjson.JSON;
import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.BeanUtil;
import com.starter.medicalcommon.util.DateUtil;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.*;
import com.starter.medicaldao.entity.dto.DoctorDto;
import com.starter.medicaldao.entity.dto.HospitalDto;
import com.starter.medicaldao.entity.filter.DoctorFilter;
import com.starter.medicaldao.mapper.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/manage/hospital")
@Api(tags = "Z管理后台#######医院管理")
public class HospitalController {

    @Resource
    private HospitalMapper hospitalMapper;
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private ContractMapper contractMapper;

    @GetMapping("/list")
    @ApiOperation("管理后台#######医院列表")
    public BaseResponse listElder(String province,
                                  String city,
                                  String country,
                                  String town,
                                  String community,
                                  Integer offset,
                                  Integer pageSize) {
        log.info("/manage/hospital/list province:{} city:{} country:{} town:{} community:{} offset:{} pageSize:{}",
                province, city, country, town, community, offset, pageSize);
        BaseResponse<List<HospitalDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<Hospital> hospitalList = hospitalMapper.queryList(province, city, country,town, community, offset, pageSize);
        Integer nCount = hospitalMapper.queryCount(province, city, country,town, community);
        List<HospitalDto> hospitalDtoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(hospitalList)) {
            hospitalList.stream().filter(hospital -> hospital != null).forEach(hospital -> {
                HospitalDto hospitalDto = new HospitalDto();
                BeanUtils.copyProperties(hospital, hospitalDto);
                // 医生个数
                List<Doctor> doctorList = doctorMapper.selectByHospital(hospital.getId());
                hospitalDto.setDoctorCount(doctorList.size());
                if (hospital.getCreateTime() != null) {
                    hospitalDto.setCreateTimeStr(DateUtil.dateToString(hospital.getCreateTime()));
                }
                // 所有医生的ID查询老人个数
                List<String> doctorIdList = new ArrayList<>();
                if (!CollectionUtils.isEmpty(doctorList)) {
                    doctorList.stream().filter(doctor -> doctor != null).forEach(doctor ->{
                        doctorIdList.add(doctor.getId());
                    });
                }
                Integer count = contractMapper.selectCountByDoctorIdList(doctorIdList);
                hospitalDto.setElderCount(count);
                hospitalDtoList.add(hospitalDto);
            });
        }
        response.setCount(nCount);
        response.setData(hospitalDtoList);
        return response;
    }

    @PostMapping("/insert")
    @ApiOperation("添加医院")
    public BaseResponse create(@RequestBody Hospital one) {
        log.info("/manage/hospital/insert one:{}", one);
        Date now = new Date();
        one.setId(UUIDUtil.getUUID());
        one.setCreateTime(now);
        one.setModifyTime(now);
        int result = hospitalMapper.insertSelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @PostMapping("/update")
    @ApiOperation("更新医院")
    public BaseResponse update(@RequestBody Hospital one) {
        log.info("/manage/hospital/update one:{}", one);
        Date now = new Date();
        one.setModifyTime(now);
        int result = hospitalMapper.updateByPrimaryKeySelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @GetMapping("/detail")
    @ApiOperation("查看医院详情")
    public BaseResponse detail(String hospitalId) {
        log.info("/manage/hospital/detail hospitalId:{}", hospitalId);
        Hospital hospital = hospitalMapper.selectByPrimaryKey(hospitalId);
        HospitalDto hospitalDto = new HospitalDto();
        BeanUtils.copyProperties(hospital, hospitalDto);
        if (hospital.getCreateTime() != null) {
            hospitalDto.setCreateTimeStr(DateUtil.dateToString(hospital.getCreateTime()));
        }
        BaseResponse<HospitalDto> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(hospitalDto);
        return response;
    }

    @PostMapping("/delete")
    @ApiOperation("删除医院")
    public BaseResponse delete(String id) {
        log.info("/manage/agency/delete id:{}", id);
        int result = hospitalMapper.deleteByPrimaryKey(id);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

}