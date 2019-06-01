package com.cq.httpapi.demo.controller;

import com.cq.httpapi.demo.service.CQService.CardService;
import com.cq.httpapi.demo.service.CQService.PurchaseService;
import com.cq.httpapi.demo.service.CQService.RemindService;
import com.cq.httpapi.demo.service.CQService.TowerService;
import com.cq.httpapi.demo.testCS.testClass;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@ApiIgnore
@RestController
public class testController {

    @Resource
    private TowerService towerService;
    @Resource
    private RemindService remindService;
    @Resource
    private PurchaseService purchaseService;
    @Resource
    private CardService cardService;
    @Resource
    private testClass testClass;


}
