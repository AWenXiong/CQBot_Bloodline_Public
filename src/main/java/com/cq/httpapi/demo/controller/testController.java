package com.cq.httpapi.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.TestDTO;
import com.cq.httpapi.demo.dto.send.ApiPath;
import com.cq.httpapi.demo.dto.send.SetGroupBan;
import com.cq.httpapi.demo.entity.Tower;
import com.cq.httpapi.demo.kit.CQGroupKit;
import com.cq.httpapi.demo.kit.ObjectKit;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.kit.UrlKit;
import com.cq.httpapi.demo.service.CardService;
import com.cq.httpapi.demo.service.PurchaseService;
import com.cq.httpapi.demo.service.RemindService;
import com.cq.httpapi.demo.service.TowerService;
import com.cq.httpapi.demo.testCS.testClass;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject test(@RequestBody Tower tower) {
        try {
            String answer = towerService.getAnswer("1", "TestGuild");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("answer", answer);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }

    }

    @RequestMapping(value = "/bantest")
    public void banTest() {
        try {
            SetGroupBan sendSingleBan = new SetGroupBan();
            sendSingleBan.setGroup_id("927582523");
            sendSingleBan.setUser_id("502063298");
            sendSingleBan.setDuration(new Long(60));
            JSONObject jsonObject = JSON.parseObject("{\"group_id\":\"927582523\"," +
                    "\"user_id\":\"502063298\"," +
                    "\"duration\":\"60\"}");
            UrlKit.sendPost(ApiPath.SET_GROUP_BAN, jsonObject);
            System.err.println(jsonObject.toString());
            System.err.println("Sent!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/testGetGroupMembList")
    public void testGetGroupMembList() {
        CQGroupKit.getGroupAdmin("927582523");
    }

    @RequestMapping(value = "/testNextDate")
    @ResponseBody
    public JSONObject testNextWeekday(@RequestBody TestDTO testDTO) {
        JSONObject jsonObject = new JSONObject();
        String msg = testDTO.getMsg();
        jsonObject.put("res", TimeKit.getDate(Integer.parseInt(msg)));
        return jsonObject;
    }


    @RequestMapping(value = "/favicon")
    public JSONObject favicon() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", 1);
        return jsonObject;
    }

    @RequestMapping(value = "/timetest")
    @ResponseBody
    public JSONObject timeTest(@RequestBody TestDTO testDTO) {
        JSONObject jsonObject = new JSONObject();
        String msg = testDTO.getMsg();
        Date GMT = TimeKit.convertToGMT(TimeKit.parseTime(msg), "GMT+8");
        System.err.println(TimeKit.parseTime(GMT));
        Date LATime = TimeKit.convertGMTTo(GMT, "America/Los_Angeles");
        jsonObject.put("res", TimeKit.parseTime(LATime));
        return jsonObject;
    }

    @RequestMapping(value = "/remindtest")
    @ResponseBody
    public JSONObject remindTest(@RequestBody TestDTO testDTO) {
        JSONObject jsonObject = new JSONObject();
        String msg = testDTO.getMsg();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
        calendar.setTime(TimeKit.parseTime(msg));
        jsonObject.put("res", calendar.get(Calendar.DAY_OF_WEEK));
        return jsonObject;
    }

    @RequestMapping(value = "/inputtest")
    @ResponseBody
    public JSONObject inputTest(@RequestBody TestDTO testDTO) {
        JSONObject jsonObject = new JSONObject();
        String msg = testDTO.getMsg();
        Date startTime1 = new Date();
        JSONObject res = ObjectKit.getJSONObjectFromFileUsingBufferedInputStream(msg);
        Date endTime1 = new Date();
        ObjectKit.getJSONObjectFromFileUsingBufferedReader(msg);
        Date endTime2 = new Date();
        jsonObject.put("res1", startTime1.getTime() - endTime1.getTime());
        jsonObject.put("res2", endTime1.getTime() - endTime2.getTime());
        jsonObject.put("json", res);
        return jsonObject;
    }

    @RequestMapping(value = "/testMethod")
    @ResponseBody
    public JSONObject testMethod(@RequestBody TestDTO testDTO) {
        testClass.testMethod();
        return new JSONObject();
    }

}
