package com.starter.medicalservice.service;

import com.starter.medicalcommon.enums.MsgCodeEnum;
import com.starter.medicalcommon.util.UUIDUtil;
import com.starter.medicalcommon.vo.request.UserLoginRequest;
import com.starter.medicalcommon.vo.request.UserRegisterRequest;
import com.starter.medicalcommon.vo.response.BaseResponse;
import com.starter.medicaldao.entity.Manager;
import com.starter.medicaldao.entity.Younger;
import com.starter.medicaldao.mapper.ManagerMapper;
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
public class ManagerService {

    @Resource
    private ManagerMapper managerMapper;

    public BaseResponse register(UserRegisterRequest request) {
        Manager oldOne = managerMapper.selectByPhone(request.getPhone());

        if (oldOne != null) {
            return new BaseResponse<>(MsgCodeEnum.USER_EXIST_ERROR);
        }

        //TODO 校验手机验证码

        Date now = new Date();
        Manager manager = new Manager();
        manager.setId(UUIDUtil.getUUID());
        manager.setPhone(request.getPhone());
        manager.setPwd(DigestUtils.md5Hex(request.getPwd() + PASSWORD_SALT));
        manager.setName(request.getName());
        manager.setCreateTime(now);
        manager.setModifyTime(now);
        int result = managerMapper.insertSelective(manager);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.USER_REGISTER_ERROR);
    }

    public BaseResponse login(UserLoginRequest request) {
        Manager one = managerMapper.selectByPhoneAndPwd(request.getPhone(),
                DigestUtils.md5Hex(request.getPwd() + PASSWORD_SALT));

        if (one == null) {
            return new BaseResponse<>(MsgCodeEnum.USER_PASSWORD_ERROR);
        }

        return BaseResponse.successResponse();
    }

    public BaseResponse queryByPhone(String phone) {
        Manager manager = managerMapper.selectByPhone(phone);

        if (manager != null) {
            manager.setPwd("");
        }

        BaseResponse<Manager> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(manager);
        return response;
    }

    public BaseResponse<Manager> query(String userId) {
        Manager manager = managerMapper.selectByPrimaryKey(userId);
        BaseResponse<Manager> response = new BaseResponse<>(MsgCodeEnum.SUCCESS);
        response.setData(manager);
        return response;
    }

    public BaseResponse update(Manager manager) {
        manager.setModifyTime(new Date());
        int result = managerMapper.updateByPrimaryKeySelective(manager);
        return result > 0 ? BaseResponse.successResponse() : new BaseResponse(MsgCodeEnum.OPERATION_FAIL_ERROR);
    }
}