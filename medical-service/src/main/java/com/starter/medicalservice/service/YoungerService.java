package com.starter.medicalservice.service;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.request.UserLoginRequest;
import com.starter.medicalcommon.vo.request.UserRegisterRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicaldao.entity.Younger;
import com.starter.medicaldao.mapper.YoungerMapper;
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
public class YoungerService {

    @Resource
    private YoungerMapper youngerMapper;

    public BaseResponse register(UserRegisterRequest request) {
        Younger oldOne = youngerMapper.selectByPhone(request.getPhone());

        if (oldOne != null) {
            return new BaseResponse<>(MsgCodeEnum.USER_EXIST_ERROR);
        }

        //TODO 校验手机验证码

        Date now = new Date();
        Younger younger = new Younger();
        younger.setId(UUIDUtil.getUUID());
        younger.setPhone(request.getPhone());
        younger.setPwd(DigestUtils.md5Hex(request.getPwd() + PASSWORD_SALT));
        younger.setName(request.getName());
        younger.setCreateTime(now);
        younger.setModifyTime(now);
        int result = youngerMapper.insertSelective(younger);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.USER_REGISTER_ERROR);
    }

    public BaseResponse login(UserLoginRequest request) {
        Younger one = youngerMapper.selectByPhoneAndPwd(request.getPhone(),
                DigestUtils.md5Hex(request.getPwd() + PASSWORD_SALT));

        if (one == null) {
            return new BaseResponse<>(MsgCodeEnum.USER_PASSWORD_ERROR);
        }

        return BaseResponse.successResponse();
    }

    public BaseResponse queryByPhone(String phone) {
        Younger younger = youngerMapper.selectByPhone(phone);

        if (younger != null) {
            younger.setPwd("");
        }

        BaseResponse<Younger> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(younger);
        return response;
    }

    public BaseResponse<Younger> query(String userId) {
        Younger younger = youngerMapper.selectByPrimaryKey(userId);
        BaseResponse<Younger> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(younger);
        return response;
    }

    public BaseResponse update(Younger younger) {
        younger.setModifyTime(new Date());
        int result = youngerMapper.updateByPrimaryKeySelective(younger);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }
}