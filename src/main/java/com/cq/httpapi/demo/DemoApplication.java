package com.cq.httpapi.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.cq.httpapi.demo.dao")
@ComponentScan(basePackages = "com.cq.httpapi.demo.service")
@ComponentScan(basePackages = "com.cq.httpapi.demo.testCS")
@ComponentScan(basePackages = "com.cq.httpapi.demo.generator")
//@EnableCaching
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

