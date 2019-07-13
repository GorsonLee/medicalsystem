package com.starter.medicalservice.service;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.request.UserLoginRequest;
import com.starter.medicalcommon.vo.request.UserRegisterRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Doctor;
import com.starter.medicaldao.entity.Manager;
import com.starter.medicaldao.mapper.DoctorMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    public BaseResponse register(UserRegisterRequest request) {
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

    public BaseResponse login(UserLoginRequest request) {
        Doctor one = doctorMapper.selectByPhoneAndPwd(request.getPhone(),
                DigestUtils.md5Hex(request.getPwd() + PASSWORD_SALT));

        if (one == null) {
            return new BaseResponse<>(MsgCodeEnum.USER_PASSWORD_ERROR);
        }

        return BaseResponse.successResponse();
    }

    public BaseResponse<Doctor> query(String doctorId) {
        Doctor doctor = doctorMapper.selectByPrimaryKey(doctorId);
        BaseResponse<Doctor> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(doctor);
        return response;
    }

    public BaseResponse update(Doctor doctor) {
        doctor.setModifyTime(new Date());
        int result = doctorMapper.updateByPrimaryKeySelective(doctor);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.USER_REGISTER_ERROR);
    }

    /**
     * 查询医生信息
     *
     * @param doctor
     * @return
     */
    public BaseResponse<List<Doctor>> queryDoctors(Doctor doctor) {
        List<Doctor> doctorList = doctorMapper.queryDoctors(doctor);
        BaseResponse<List<Doctor>> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(doctorList);
        return response;
    }

    public BaseResponse queryByPhone(String phone) {
        Doctor doctor = doctorMapper.selectByPhone(phone);

        if (doctor != null) {
            doctor.setPwd("");
        }

        BaseResponse<Doctor> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(doctor);
        return response;
    }
}