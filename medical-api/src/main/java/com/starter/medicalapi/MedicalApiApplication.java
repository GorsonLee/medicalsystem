package com.starter.medicalapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-03-16 16:32
 **/
@SpringBootApplication
@MapperScan(basePackages = {"com.starter.**.mapper"})
@ComponentScan(basePackages = { "com.starter" })
public class MedicalApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalApiApplication.class, args);
    }

}
