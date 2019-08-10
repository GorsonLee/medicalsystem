package com.starter.medicalapi.controller.manage.system;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.DateUtil;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Care;
import com.starter.medicaldao.entity.New;
import com.starter.medicaldao.entity.dto.CareDto;
import com.starter.medicaldao.entity.dto.NewDto;
import com.starter.medicaldao.mapper.CareMapper;
import com.starter.medicaldao.mapper.NewMapper;
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
@RequestMapping("/manage/news")
@Api(tags = "咨询")
public class NewsController {


    @Resource
    private NewMapper newMapper;

    @GetMapping("/list")
    @ApiOperation("关怀列表")
    public BaseResponse list(Integer type, Integer offset,
                             Integer pageSize) {
        BaseResponse<List<NewDto>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        List<New> newList = newMapper.selectByType(type, offset, pageSize);
        List<NewDto> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(newList)) {
            newList.stream().filter(obj -> obj != null).forEach(obj -> {
                NewDto dto = new NewDto();
                BeanUtils.copyProperties(obj, dto);
                if (obj.getCreateTime() != null) {
                    dto.setCreateTimeStr(DateUtil.dateToString(obj.getCreateTime()));
                }
                resultList.add(dto);
            });
        }
        response.setData(resultList);
        return response;
    }

    @PostMapping("/insert")
    @ApiOperation("新增咨询")
    public BaseResponse create(@RequestBody New one) {
        Date now = new Date();
        one.setId(UUIDUtil.getUUID());
        one.setCreateTime(now);
        one.setModifyTime(now);
        int result = newMapper.insertSelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @PostMapping("/update")
    @ApiOperation("更新咨询")
    public BaseResponse update(@RequestBody New one) {
        Date now = new Date();
        one.setModifyTime(now);
        int result = newMapper.updateByPrimaryKeySelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @GetMapping("/detail")
    @ApiOperation("查看咨询详情")
    public BaseResponse detail(String newId) {
        New aNew = newMapper.selectByPrimaryKey(newId);
        BaseResponse<New> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(aNew);
        return response;
    }

    @PostMapping("/delete")
    @ApiOperation("删除咨询")
    public BaseResponse update(String careId) {
        int result = newMapper.deleteByPrimaryKey(careId);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }


}