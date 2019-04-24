package com.cq.httpapi.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@MapperScan("com.cq.httpapi.demo.dao")
@ComponentScan(basePackages = "com.cq.httpapi.demo.service")
@ComponentScan(basePackages = "com.cq.httpapi.demo.testCS")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

