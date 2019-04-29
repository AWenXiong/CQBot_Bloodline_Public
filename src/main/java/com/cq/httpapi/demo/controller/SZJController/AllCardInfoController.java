package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.*;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.*;
import com.cq.httpapi.demo.exception.SZJException.AllCardsInfoException.*;
import com.cq.httpapi.demo.service.SZJService.*;
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
    private SZJCardInfoService szjCardInfoService;
    @Resource
    private SZJCardInfoOtherNameService szjCardInfoOtherNameService;
    @Resource
    private SZJCardInfoExtService szjCardInfoExtService;
    @Resource
    private SZJSpellInfoService szjSpellInfoService;
    @Resource
    private SZJEnemyInfoService szjEnemyInfoService;
    @Resource
    private SZJEnemyLevelService szjEnemyLevelService;
    @Resource
    private SZJEnemyCardService szjEnemyCardService;

    /**
     * 获取全部卡牌信息
     *
     * @param request
     * @return errorCode = 0  正常
     * errorCode = 1  登录码不存在
     *
     */
    @RequestMapping(value = "/GetAllCardsInfo", method = RequestMethod.POST)
    public GetAllCardsInfoResponse getAllCardsInfo(@RequestBody GetAllCardsInfoRequest request) {
        GetAllCardsInfoResponse response = new GetAllCardsInfoResponse();
        try {
            ArrayList<GetAllCardsInfoResponseData> datas = szjCardInfoService.getAllCards(request);
            response.setSuccess(true);
            response.setData(datas);
        } catch (GetAllCardsInfoException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
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
        try {
            ArrayList<GetAllCardsOtherNameResponseData> datas = szjCardInfoOtherNameService.getAllCardNickname(request);
            response.setData(datas);
            response.setSuccess(true);
        } catch (GetAllCardsOtherNameException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
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
        try {
            ArrayList<GetAllCardsInfoExtResponseData> datas = szjCardInfoExtService.getAllCardInfoExt(request);
            response.setData(datas);
            response.setSuccess(true);
        } catch (GetAllCardsInfoExtException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
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
        GetAllSpellsInfoResponse response = new GetAllSpellsInfoResponse();
        try {
            ArrayList<GetAllSpellsInfoResponseData> data = szjSpellInfoService.getAllSpells(request);
            response.setData(data);
            response.setSuccess(true);
        } catch (GetAllSpellsInfoException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
        }
        return response;
    }

    /**
     * 获取敌阵容信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetEnemyInfo", method = RequestMethod.POST)
    public GetEnemyInfoResponse getEnemyInfo(@RequestBody GetEnemyInfoRequest request) {
        GetEnemyInfoResponse response = new GetEnemyInfoResponse();
        try {
            ArrayList<GetEnemyInfoResponseData> data = szjEnemyInfoService.getEnemyInfo(request);
            response.setData(data);
            response.setSuccess(true);
        } catch (GetEnemyInfoException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
        }
        return response;
    }

    /**
     * 获取敌阵容关信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetEnemyLevel", method = RequestMethod.POST)
    public GetEnemyLevelResponse getEnemyLevel(@RequestBody GetEnemyLevelRequest request) {
        GetEnemyLevelResponse response = new GetEnemyLevelResponse();
        try {
            ArrayList<GetEnemyLevelResponseData> data = szjEnemyLevelService.getEnemyInfo(request);
            response.setData(data);
            response.setSuccess(true);
        } catch (GetEnemyLevelException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
        }
        return response;
    }

    /**
     * 获取敌阵容卡信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetEnemyCard", method = RequestMethod.POST)
    public GetEnemyCardResponse getEnemyCard(@RequestBody GetEnemyCardRequest request) {
        GetEnemyCardResponse response = new GetEnemyCardResponse();
        try {
            ArrayList<GetEnemyCardResponseData> data = szjEnemyCardService.getEnemyInfo(request);
            response.setData(data);
            response.setSuccess(true);
        } catch (GetEnemyCardException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
        }
        return response;
    }
}
