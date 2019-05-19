package com.starter.medicalapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-03-16 22:02
 **/
@RestController
public class DebugController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
