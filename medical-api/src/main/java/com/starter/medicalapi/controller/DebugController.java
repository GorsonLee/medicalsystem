package com.starter.medicalapi.controller;

import com.starter.medicaldao.entity.User;
import com.starter.medicaldao.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-03-16 22:02
 **/
@RestController
public class DebugController {

    @Resource
    UserMapper userMapper;

    @GetMapping("/hello")
    public String hello() {
        User user = new User();
        user.setId(String.valueOf(System.currentTimeMillis()));
        user.setPhone("phone");
        user.setPwd("pwd");
        user.setCreateTime(new Date());
        user.setModifyTime(new Date());
        userMapper.insertSelective(user);
        return "Hello";
    }
}
