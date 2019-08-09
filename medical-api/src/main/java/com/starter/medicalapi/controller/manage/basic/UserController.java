package com.starter.medicalapi.controller.manage.basic;

import com.alibaba.fastjson.JSON;
import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Agency;
import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.entity.Younger;
import com.starter.medicaldao.mapper.AgencyMapper;
import com.starter.medicaldao.mapper.ElderMapper;
import com.starter.medicaldao.mapper.YoungerMapper;
import com.starter.medicalservice.service.ElderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@RestController
@RequestMapping("/manage/user")
@Api(tags = "用户管理")
public class UserController {

    @Resource
    private AgencyMapper agencyMapper;
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private YoungerMapper youngerMapper;
    @Resource
    private ElderService elderService;

    @GetMapping("/listElder")
    @ApiOperation("列表老人")
    public BaseResponse listElder(Integer provideState,
                                  String agencyId,
                                  Integer birth,
                                  Integer verifyState,
                                  Integer offset,
                                  Integer pageSize) {
        List<Elder> elderList = elderMapper.listElder(provideState, agencyId, birth,verifyState, offset, pageSize);
        BaseResponse<List<JSONObject>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(elderList.stream().map(item -> {
            JSONObject json = (JSONObject) JSON.toJSON(item);

//            Agency agency = agencyMapper.selectByPrimaryKey(item.get)


            return json;
        }).collect(Collectors.toList()));
        return response;
    }

    @GetMapping("/listYounger")
    @ApiOperation("列表子女")
    public BaseResponse listYounger(String province,
                             String city,
                             String name,
                             Integer offset,
                             Integer pageSize) {
        List<Agency> agencyList = agencyMapper.selectByParams(province, city, name, offset, pageSize);
        BaseResponse<List<JSONObject>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(agencyList.stream().map(item -> {
            JSONObject json = (JSONObject) JSON.toJSON(item);
            json.put("elderCount", elderMapper.countByOrganizationId(item.getId()));
            return json;
        }).collect(Collectors.toList()));
        return response;
    }

    @PostMapping("/insertElder")
    @ApiOperation("添加老人")
    public BaseResponse create(@RequestBody Elder one) {
        Date now = new Date();
        one.setId(UUIDUtil.getUUID());
        one.setCreateTime(now);
        one.setModifyTime(now);
        int result = elderMapper.insertSelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @PostMapping("/update")
    @ApiOperation("更新老人")
    public BaseResponse update(@RequestBody Elder one) {
        Date now = new Date();
        one.setModifyTime(now);
        int result = elderMapper.updateByPrimaryKeySelective(one);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }

    @GetMapping("/queryElder")
    @ApiOperation("查看老人详情")
    public BaseResponse queryElder(String userId) {
        return elderService.query(userId);
    }



    @PostMapping("/insert")
    @ApiOperation("添加子女")
    public BaseResponse insert(@RequestBody Younger one) {
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
        Younger younger = youngerMapper.selectByPrimaryKey(userId);
        BaseResponse<Younger> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(younger);
        return response;
    }

}