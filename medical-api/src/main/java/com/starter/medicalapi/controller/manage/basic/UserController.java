package com.starter.medicalapi.controller.manage.basic;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.DateUtil;
import com.starter.medicalcommon.util.StringUtil;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.*;
import com.starter.medicaldao.entity.dto.*;
import com.starter.medicaldao.entity.filter.ElderFilter;
import com.starter.medicaldao.mapper.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/manage/user")
@Api(tags = "Z管理后台#######用户管理")
public class UserController {

    @Resource
    private AgencyMapper agencyMapper;
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private YoungerMapper youngerMapper;
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private ContractMapper contractMapper;
    @Resource
    private RelationShipMapper relationShipMapper;
    @Resource
    private AssociationMapper associationMapper;
    @Resource
    private BodySignMapper bodySignMapper;
    @Resource
    private HealthDocumentMapper healthDocumentMapper;

    @GetMapping("/listElder")
    @ApiOperation("列表老人")
    public BaseResponse listElder(Integer provideState,
                                  String agencyId,
                                  Integer birth,
                                  Integer offset,
                                  Integer pageSize) {
        log.info("/manage/user/listElder provideState:{} agencyId:{} birth:{} offset:{} pageSize:{}", provideState, agencyId, birth, offset, pageSize);
        BaseResponse<List<ElderRelationContractDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<ElderRelationContractDto> resultList = new ArrayList<>();

        ElderFilter filter = new ElderFilter();
        filter.setProvideState(provideState);
        filter.setAgencyId(agencyId);
        filter.setBirth(birth);
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<Elder> elderList = elderMapper.listElder(provideState, agencyId, birth, null, offset, pageSize);
        int count = elderMapper.listCountElder(provideState, agencyId, birth, null);

        if (!CollectionUtils.isEmpty(elderList)) {
           elderList.stream().filter(Objects::nonNull).forEach(elder -> {
               ElderRelationContractDto dto = new ElderRelationContractDto();
               BeanUtils.copyProperties(elder, dto);
               // 机构的名称
               Agency agency = agencyMapper.selectByPrimaryKey(elder.getAgencyId());
               if (Objects.nonNull(agency)) {
                   dto.setAgencyName(agency.getName());
               }
               resultList.add(dto);
           });
        }
        response.setCount(count);
        response.setData(resultList);
        return response;
    }

    @GetMapping("/listYounger")
    @ApiOperation("列表子女")
    public BaseResponse listYounger(Integer offset,
                                     Integer pageSize) {
        log.info("/manage/user/listYounger offset:{} pageSize:{}", offset, pageSize);
        BaseResponse<List<Younger>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);

        List<Younger> youngerList = youngerMapper.readList(offset, pageSize);
        int count = youngerMapper.selectCount();
        response.setData(youngerList);
        response.setCount(count);
        return response;
    }

    @PostMapping("/insertElder")
    @ApiOperation("添加老人")
    public BaseResponse create(@RequestBody Elder one) {
        log.info("/manage/user/insertElder one:{}", one);
        if (Objects.nonNull(one) && Objects.nonNull(one.getBirthString())) {
            one.setBirth(StringUtil.stringBirth2Int(one.getBirthString()));
        }
        Date now = new Date();
        one.setId(UUIDUtil.getUUID());
        one.setCreateTime(now);
        one.setModifyTime(now);
        int result = elderMapper.insertSelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @PostMapping("/updateElder")
    @ApiOperation("更新老人")
    public BaseResponse updateElder(@RequestBody Elder one) {
        log.info("/manage/user/updateElder one:{}", one);
        if (Objects.nonNull(one) && Objects.nonNull(one.getBirthString())) {
            one.setBirth(StringUtil.stringBirth2Int(one.getBirthString()));
        }
        Date now = new Date();
        one.setModifyTime(now);
        int result = elderMapper.updateByPrimaryKeySelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @PostMapping("/updateYounger")
    @ApiOperation("更新子女")
    public BaseResponse updateYouger(@RequestBody Younger one) {
        log.info("/manage/user/updateYounger one:{}", one);
        if (Objects.nonNull(one) && Objects.nonNull(one.getBirthString())) {
            one.setBirth(StringUtil.stringBirth2Int(one.getBirthString()));
        }
        Date now = new Date();
        one.setModifyTime(now);
        int result =youngerMapper.updateByPrimaryKeySelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @GetMapping("/queryElder")
    @ApiOperation("查看老人详情")
    public BaseResponse queryElder(String userId) {
        log.info("/manage/user/queryElder userId:{}", userId);
        BaseResponse<DetailElderDto> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        DetailElderDto result = new DetailElderDto();
        List<RelationDto> relationList = new ArrayList<>();
        List<DoctorDtos> doctorList = new ArrayList<>();
        List<BodySign> bodysignList = new ArrayList<>();
        List<HealthDocument> healthdDocumentList = new ArrayList<>();

        // 查询老人详情
        Elder elder = elderMapper.selectByPrimaryKey(userId);
        if (Objects.isNull(elder)) {
            return new BaseResponse(MsgCodeEnum.USER_NOT_FOUND_ERROR);
        }
        // 机构名称
        Agency agency = agencyMapper.selectByPrimaryKey(elder.getAgencyId());
        BeanUtils.copyProperties(elder, result);
        if (Objects.nonNull(agency)) {
            result.setAgencyName(agency.getName());
        }

        // 老人签约的医生列表
        List<Contract> contractList = contractMapper.selectByUserId(userId);
        if (!CollectionUtils.isEmpty(contractList)) {
            contractList.stream().filter(Objects::nonNull).forEach(contract -> {
                DoctorDtos doctorDtos = new DoctorDtos();
                Doctor doctor = doctorMapper.selectByPrimaryKey(contract.getDoctorId());
                BeanUtils.copyProperties(doctor, doctorDtos);
                doctorList.add(doctorDtos);
            });
        }

        // 老人被关联列表(子女/老人)
        List<Association> associationList = associationMapper.selectByAssociateUserId(userId);
        if (!CollectionUtils.isEmpty(associationList)) {
            associationList.stream().filter(Objects::nonNull).forEach(association -> {
                RelationDto youngerDto = new RelationDto();

                // 子女关联
                if (association.getAssociateRole().equals(0)) {
                    Younger younger = youngerMapper.selectByPrimaryKey(association.getUserId());
                    if (Objects.nonNull(younger)) {
                        BeanUtils.copyProperties(younger, youngerDto);
                    }
                // 老人关联
                }else if (association.getAssociateRole().equals(1)) {
                    Elder relationElder = elderMapper.selectByPrimaryKey(association.getUserId());
                    if (Objects.nonNull(relationElder)) {
                        BeanUtils.copyProperties(relationElder, youngerDto);
                    }
                }
                relationList.add(youngerDto);
            });
        }
        // 生命体征列表
        bodysignList = bodySignMapper.selectByUserId(userId);

        // 健康档案列表
        healthdDocumentList = healthDocumentMapper.selectByUserId(userId);

        result.setDoctorList(doctorList);
        result.setRelationList(relationList);
        result.setBodySignList(bodysignList);
        result.setHealthDocumentList(healthdDocumentList);
        response.setData(result);
        return response;
    }



    @PostMapping("/insert")
    @ApiOperation("添加子女")
    public BaseResponse insert(@RequestBody Younger one) {
        if (Objects.nonNull(one) && Objects.nonNull(one.getBirthString())) {
            one.setBirth(StringUtil.stringBirth2Int(one.getBirthString()));
        }
        log.info("/manage/user/insert one:{}", one);
        Date now = new Date();
        one.setId(UUIDUtil.getUUID());
        one.setCreateTime(now);
        one.setModifyTime(now);
        int result = youngerMapper.insertSelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @GetMapping("/queryChild")
    @ApiOperation("查看子女详情")
    public BaseResponse queryChild(String userId) {
        log.info("/manage/user/queryChild userId:{}", userId);
        BaseResponse<DetailYougerDto> response = new BaseResponse<DetailYougerDto>(MsgCodeEnum.SUCCESS);
        List<ElderDto> elderList = new ArrayList<>();

        Younger younger = youngerMapper.selectByPrimaryKey(userId);
        if (Objects.isNull(younger)) {
            return new BaseResponse(MsgCodeEnum.USER_NOT_FOUND_ERROR);
        }
        DetailYougerDto result = new DetailYougerDto();
        BeanUtils.copyProperties(younger, result);
        if (younger.getCreateTime() != null) {
            result.setCreateTimeStr(DateUtil.dateToString(younger.getCreateTime()));
        }

        // 关联的老人列表
        List<Association> associationList = associationMapper.selectByUserId(userId);
        if (!CollectionUtils.isEmpty(associationList)) {
            associationList.stream().filter(Objects::nonNull).forEach(association -> {
                // 老人资料
                Elder elder = elderMapper.selectByPrimaryKey(association.getUserId());
                ElderDto elderDto = new ElderDto();
                BeanUtils.copyProperties(elder, elderDto);
                elderList.add(elderDto);
            });
        }
        result.setElderList(elderList);
        response.setData(result);
        return response;
    }

}