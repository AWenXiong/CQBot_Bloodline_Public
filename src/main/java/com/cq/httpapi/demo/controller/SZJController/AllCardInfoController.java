package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.*;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.*;
import com.cq.httpapi.demo.entity.SZJ.Szjcardinfo;
import com.cq.httpapi.demo.entity.SZJ.Szjcardinfoext;
import com.cq.httpapi.demo.entity.SZJ.Szjcardinfoothername;
import com.cq.httpapi.demo.service.SZJCardInfoExtService;
import com.cq.httpapi.demo.service.SZJCardInfoOtherNameService;
import com.cq.httpapi.demo.service.SZJCardInfoService;
import com.cq.httpapi.demo.service.SZJUserInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/szj")
public class AllCardInfoController {

    @Resource
    private SZJUserInfoService szjUserInfoService;
    @Resource
    private SZJCardInfoService szjCardInfoService;
    @Resource
    private SZJCardInfoOtherNameService szjCardInfoOtherNameService;
    @Resource
    private SZJCardInfoExtService szjCardInfoExtService;

    /**
     * 获取全部卡牌信息
     *
     * @param request
     * @return errorCode = 0  正常
     * errorCode = 1  登录码不存在
     * errorCode = 2  获取全部卡牌信息失败(data is a null pointer)
     */
    @RequestMapping(value = "/GetAllCardsInfo", method = RequestMethod.POST)
    public GetAllCardsInfoResponse getAllCardsInfo(@RequestBody GetAllCardsInfoRequest request) {
        GetAllCardsInfoResponse response = new GetAllCardsInfoResponse();
        String openId = request.getOpenid();
        if (openId != null && !szjUserInfoService.existOpenId(openId)) {
            response.setError(false, 1, "登录码不存在");
        } else {
            ArrayList<Szjcardinfo> cards = szjCardInfoService.getAllCards();
            if (cards == null) {
                response.setError(false, 2, "获取全部卡牌信息失败");
            } else {
                response.setSuccess(true);
                response.setData(cards);
            }
        }
        return response;
    }

    /**
     * 获取全部卡牌别名
     *
     * @param request
     * @return errorCode = 0  正常
     * errorCode = 1  登录码不存在
     * errorCode = 2  获取全部卡牌别名失败(data is a null pointer)
     */
    @RequestMapping(value = "/GetAllCardsOtherName", method = RequestMethod.POST)
    public GetAllCardsOtherNameResponse getAllCardsOtherName(@RequestBody GetAllCardsOtherNameRequest request) {
        GetAllCardsOtherNameResponse response = new GetAllCardsOtherNameResponse();
        String openId = request.getOpenid();
        if (openId != null && !szjUserInfoService.existOpenId(openId)) {
            response.setError(false, 1, "登录码不存在");
        } else {
            ArrayList<Szjcardinfoothername> cards = szjCardInfoOtherNameService.getAllCardNickname();
            if (cards == null) {
                response.setError(false, 2, "获取全部卡牌别名失败");
            } else {
                response.setSuccess(true);
                response.setData(cards);
            }
        }
        return response;
    }

    /**
     * 获取全部卡牌扩展信息
     *
     * @param request
     * @return errorCode = 0  正常
     * errorCode = 1  登录码不存在
     * errorCode = 2  获取全部卡牌扩展信息失败(data is a null pointer)
     */
    @RequestMapping(value = "/GetAllCardsInfoExt", method = RequestMethod.POST)
    public GetAllCardsInfoExtResponse getAllCardsInfoExt(@RequestBody GetAllCardsInfoExtRequest request) {
        GetAllCardsInfoExtResponse response = new GetAllCardsInfoExtResponse();
        String openId = request.getOpenid();
        if (openId != null && !szjUserInfoService.existOpenId(openId)) {
            response.setError(false, 1, "登录码不存在");
        } else {
            ArrayList<Szjcardinfoext> exts = szjCardInfoExtService.getAllCardInfoExt();
            if (exts == null) {
                response.setError(false, 2, "获取全部卡牌扩展信息失败");
            } else {
                response.setSuccess(true);
                response.setData(exts);
            }
        }
        return response;
    }

    /**
     * 获取全部法阵信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetAllSpellsInfo", method = RequestMethod.POST)
    public GetAllSpellsInfoResponse getAllSpellsInfo(@RequestBody GetAllSpellsInfoRequest request) {
        return null;
    }

    /**
     * 获取敌阵容信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetEnemyInfo", method = RequestMethod.POST)
    public GetEnemyInfoResponse getEnemyInfo(@RequestBody GetEnemyInfoRequest request) {
        return null;
    }

    /**
     * 获取敌阵容关信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetEnemyLevel", method = RequestMethod.POST)
    public GetEnemyLevelResponse getEnemyLevel(@RequestBody GetEnemyLevelRequest request) {
        return null;
    }

    /**
     * 获取敌阵容卡信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetEnemyCard", method = RequestMethod.POST)
    public GetEnemyCardResponse getEnemyCard(@RequestBody GetEnemyCardRequest request) {
        return null;
    }
}
