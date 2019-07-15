package com.cq.httpapi.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.service.SZJService.SZJCardInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@ApiIgnore
@RestController
public class testController {
    @Resource
    SZJCardInfoService szjCardInfoService;

    @RequestMapping(value = "/test/init")
    public JSONObject test() {
        szjCardInfoService.init();
        return new JSONObject();
    }
}
