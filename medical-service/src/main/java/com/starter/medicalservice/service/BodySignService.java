package com.starter.medicalservice.service;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.request.BodySignInsertRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.BodySign;
import com.starter.medicaldao.mapper.BodySignMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@Service
@Slf4j
public class BodySignService {

    private static final List<String> BODY_SIGN_LIST = Arrays.asList(
            //呼吸率
            "breathRate",
            //血压，单位mmH
            "bp",
            //心率，单位次/分
            "heartRate",
            //血氧饱和度，单位SaO2
            "spo2",
            //睡眠时间，单位min
            "sleepTime",
            //血糖，单位mmol/L
            "bg",
            //体温，单位°C
            "temperature",
            //尿酸，单位umol/L
            "uricAcid",
            //血脂，单位mmol/L
            "bloodFat",
            //体重，单位kg
            "weight",
            //身高，单位cm
            "height",
            //腰围，单位cm
            "waist",
            //臀围，单位cm
            "hip"
    );

    @Resource
    private BodySignMapper bodySignMapper;

    /**
     * 插入体征数据
     *
     * @param request
     * @return
     */
    public BaseResponse insertSelective(BodySignInsertRequest request) {
        Date now = new Date();
        BodySign bodySign = new BodySign();
        bodySign.setId(UUIDUtil.getUUID());
        bodySign.setUserId(request.getUserId());
        bodySign.setBodySign(request.getBodySign());
        bodySign.setContent(request.getContent());
        bodySign.setDescription(request.getDescription());
        bodySign.setCreateTime(now);
        bodySign.setModifyTime(now);
        int result = bodySignMapper.insertSelective(bodySign);
        return result > 0 ? BaseResponse.successResponse() : BaseResponse.failResponse();
    }

    /**
     * 查询用户的某个体征数据
     *
     * @param userId
     * @param bodySign
     * @param offset
     * @param pageSize
     * @return
     */
    public BaseResponse<List<BodySign>> queryByUserIdAndBodySign(String userId,
                                                                 String bodySign,
                                                                 Integer offset,
                                                                 Integer pageSize) {
        List<BodySign> bodySignList = bodySignMapper.selectByUserIdAndBodySign(userId,
                bodySign,
                offset,
                pageSize);
        BaseResponse<List<BodySign>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(bodySignList);
        return response;
    }

    /**
     * 获取用户的体征概览
     *
     * @param userId
     * @return
     */
    public BaseResponse<Map<String, BodySign>> queryBodySignOverView(String userId) {
        Map<String, BodySign> bodySignMap = new HashMap<>(BODY_SIGN_LIST.size());

        BODY_SIGN_LIST.forEach(item -> {
            List<BodySign> bodySignList = bodySignMapper.selectByUserIdAndBodySign(userId,
                    item,
                    0,
                    1);

            if (!CollectionUtils.isEmpty(bodySignList)) {
                bodySignMap.put(item, bodySignList.get(0));
            } else {
                bodySignMap.put(item, new BodySign());
            }
        });

        BaseResponse<Map<String, BodySign>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(bodySignMap);
        return response;
    }
}