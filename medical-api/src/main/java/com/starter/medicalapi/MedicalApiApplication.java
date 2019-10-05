package com.starter.medicalapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 此类的描述是：解决跨域
 *
 * @author Starter
 * @date 2019-03-16 16:32
 **/
@SpringBootApplication
@MapperScan(basePackages = {"com.starter.**.mapper"})
@ComponentScan(basePackages = { "com.starter" })
public class MedicalApiApplication  extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(MedicalApiApplication.class, args);
    }

        @Override
        public void addCorsMappings(CorsRegistry registry) {

            registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("Content-Type","X-Requested-With","accept,Origin",
                        "Access-Control-Request-Method","Access-Control-Request-Headers","token")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true);
        }
}
