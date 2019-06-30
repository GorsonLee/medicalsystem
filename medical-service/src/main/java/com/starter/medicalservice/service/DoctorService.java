package com.starter.medicalservice.service;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.BeanUtil;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.request.DoctorRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicaldao.mapper.DoctorMapper;
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
public class DoctorService {

    @Resource
    private DoctorMapper doctorMapper;

    public BaseResponse register(DoctorRequest request) {
        Doctor oldOne = doctorMapper.selectByPhone(request.getPhone());

        if (oldOne != null) {
            return new BaseResponse<>(MsgCodeEnum.USER_EXIST_ERROR);
        }

        //TODO 校验手机验证码

        Date now = new Date();
        Doctor doctor = new Doctor();
        doctor.setId(UUIDUtil.getUUID());
        doctor.setPhone(request.getPhone());
        doctor.setPwd(DigestUtils.md5Hex(request.getPwd() + PASSWORD_SALT));
        doctor.setName(request.getName());
        doctor.setCreateTime(now);
        doctor.setModifyTime(now);
        int result = doctorMapper.insertSelective(doctor);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.USER_REGISTER_ERROR);
    }

    public BaseResponse login(DoctorRequest request) {
        Doctor one = doctorMapper.selectByPhoneAndPwd(request.getPhone(),
                DigestUtils.md5Hex(request.getPwd() + PASSWORD_SALT));

        if (one != null) {
            return new BaseResponse<>(MsgCodeEnum.USER_PASSWORD_ERROR);
        }

        return BaseResponse.successResponse();
    }

    public BaseResponse update(DoctorRequest request) {
        Doctor doctor = new Doctor();

        try {
            BeanUtil.copyAllPropertiesByGetterAndSetter(request, doctor);
        } catch (Exception e) {
            return BaseResponse.systemErrorResponse();
        }

        doctor.setModifyTime(new Date());
        int result = doctorMapper.updateByPrimaryKeySelective(doctor);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.USER_REGISTER_ERROR);
    }
}