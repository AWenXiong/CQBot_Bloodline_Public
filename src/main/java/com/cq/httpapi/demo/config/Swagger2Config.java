package com.cq.httpapi.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket commonDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("通用API接口文档")
                .apiInfo(apiInfo("提供通用接口"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cq.httpapi.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket normalUserDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("普通用户API文档")
                .apiInfo(apiInfo("提供普通用户接口"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cq.httpapi.demo.controller"))//设置生成的Docket对应的Controller为candidate下的所有Controller
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket companyUserDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("企业用户API接口文档")
                .apiInfo(apiInfo("提供企业用户接口"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cq.httpapi.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("多莉的接口文档")
                .version("1.0")
                .build();
    }

    private ApiInfo apiInfo(String desc) {
        return new ApiInfoBuilder()
                .title("接口文档")
                .description(desc)
                .version("1.0")
                .build();
    }

}
