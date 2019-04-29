package com.cq.httpapi.demo.testCS;

import com.cq.httpapi.demo.service.CQService.CardService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class testClass {
    @Resource
    private CardService cardService;

    public void testMethod() {
        if (cardService == null) {
            System.err.println("null");
        } else {
            System.err.println("not null!");
        }
    }
}
