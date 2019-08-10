package com.starter.medicalapi.controller.manage.security;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.DateUtil;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Complaint;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.entity.Hospital;
import com.starter.medicaldao.entity.dto.ComplaintDto;
import com.starter.medicaldao.entity.dto.HospitalDto;
import com.starter.medicaldao.mapper.BodySignAlarmMapper;
import com.starter.medicaldao.mapper.ComplaintMapper;
import com.starter.medicaldao.mapper.DoctorMapper;
import com.starter.medicaldao.mapper.ElderMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/manage/complaint")
@Api(tags = "投诉管理")
public class ComplaintController {

    @Resource
    private ComplaintMapper complaintMapper;
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private ElderMapper elderMapper;

    @GetMapping("/list")
    @ApiOperation("投诉列表")
    public BaseResponse listElder(Integer offset,
                                  Integer pageSize) {
        BaseResponse<List<ComplaintDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<Complaint> complaintList = complaintMapper.selectAll(offset, pageSize);
        List<ComplaintDto> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(complaintList)) {
            complaintList.stream().filter(complaint -> complaint != null).forEach(complaint -> {
                ComplaintDto dto = new ComplaintDto();
                BeanUtils.copyProperties(complaint, dto);
                if (complaint.getCreateTime() != null) {
                    dto.setCreateTimeStr(DateUtil.dateToString(complaint.getCreateTime()));
                }

                // 当前只容许用户投诉
                Elder elder = elderMapper.selectByPrimaryKey(dto.getComplainant());
                if (Objects.nonNull(elder)) {
                    dto.setPhoe(elder.getPhone());
                    dto.setName(elder.getName());
                }
                resultList.add(dto);
            });
        }
        response.setData(resultList);
        return response;
    }

    @PostMapping("/insert")
    @ApiOperation("新增投诉")
    public BaseResponse create(@RequestBody Complaint one) {
        Date now = new Date();
        one.setId(UUIDUtil.getUUID());
        one.setCreateTime(now);
        one.setModifyTime(now);
        int result = complaintMapper.insertSelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }
}