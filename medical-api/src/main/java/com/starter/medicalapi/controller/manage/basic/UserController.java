package com.starter.medicalapi.controller.manage.basic;

import com.alibaba.fastjson.JSON;
import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Agency;
import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.mapper.AgencyMapper;
import com.starter.medicaldao.mapper.ElderMapper;
import com.starter.medicaldao.mapper.YoungerMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

}