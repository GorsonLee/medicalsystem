package com.starter.medicalservice.service;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.request.UserRegisterRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.entity.Younger;
import com.starter.medicaldao.mapper.ElderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static com.starter.medicalcommon.constant.Constant.PASSWORD_SALT;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 17:03
 **/
@Service
@Slf4j
public class ElderService {

    @Resource
    private ElderMapper elderMapper;

    public BaseResponse register(UserRegisterRequest request) {
        Elder oldOne = elderMapper.selectByPhone(request.getPhone());

        if (oldOne != null) {
            return new BaseResponse<>(MsgCodeEnum.USER_EXIST_ERROR);
        }

        //TODO 校验手机验证码

        Date now = new Date();
        Elder elder = new Elder();
        elder.setId(UUIDUtil.getUUID());
        elder.setPhone(request.getPhone());
        elder.setPwd(DigestUtils.md5Hex(request.getPwd() + PASSWORD_SALT));
        elder.setIdentity(request.getIdentity());
        elder.setName(request.getName());
        elder.setCreateTime(now);
        elder.setModifyTime(now);
        int result = elderMapper.insertSelective(elder);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.USER_REGISTER_ERROR);
    }

    public BaseResponse login(UserRegisterRequest request) {
        Elder one = elderMapper.selectByPhoneAndPwd(request.getPhone(),
                DigestUtils.md5Hex(request.getPwd() + PASSWORD_SALT));

        if (one != null) {
            return new BaseResponse<>(MsgCodeEnum.USER_PASSWORD_ERROR);
        }

        return BaseResponse.successResponse();
    }

    public BaseResponse queryByPhone(String phone) {
        Elder elder = elderMapper.selectByPhone(phone);

        if (elder != null) {
            elder.setPwd("");
        }

        BaseResponse<Elder> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(elder);
        return response;
    }

    public BaseResponse<Elder> query(String userId) {
        Elder elder = elderMapper.selectByPrimaryKey(userId);
        BaseResponse<Elder> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(elder);
        return response;
    }

    public BaseResponse update(Elder elder) {
        elder.setModifyTime(new Date());
        int result = elderMapper.updateByPrimaryKeySelective(elder);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }
}