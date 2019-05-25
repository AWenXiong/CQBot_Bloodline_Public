package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.DeleteUserCardRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.EditUserCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.GetUserCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserCardInfoResponse.DeleteUserCardResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserCardInfoResponse.EditUserCardsInfoResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserCardInfoResponse.GetUserCardsInfoResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserCardInfoResponse.GetUserCardsInfoResponseData;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.service.SZJService.SZJUserCardInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@Api(tags = "用户卡组信息控制器")
@RestController
@RequestMapping(value = "/szj")
public class UserCardInfoController {

    @Resource
    private SZJUserCardInfoService szjUserCardInfoService;
    
    @ApiOperation(value = "获取用户卡组的卡牌信息")
    @RequestMapping(value = "/GetUserCardsInfo", method = RequestMethod.POST)
    public GetUserCardsInfoResponse getUserCardsInfo(@RequestBody GetUserCardsInfoRequest request) {
        GetUserCardsInfoResponse response = new GetUserCardsInfoResponse();
        try {
            ArrayList<GetUserCardsInfoResponseData> data = szjUserCardInfoService.getCardInfo(request);
            response.setSuccess(true);
            response.setData(data);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    @ApiOperation(value = "新增/编辑用户卡组的卡牌")
    @RequestMapping(value = "/EditUserCardsInfo", method = RequestMethod.POST)
    public EditUserCardsInfoResponse editUserCardsInfo(@RequestBody EditUserCardsInfoRequest request) {
        EditUserCardsInfoResponse response = new EditUserCardsInfoResponse();
        try {
            szjUserCardInfoService.updateCardInfo(request);
            response.setSuccess(true);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    @ApiOperation(value = "用户卡牌删除(逻辑删除)")
    @RequestMapping(value = "/DeleteUserCard", method = RequestMethod.POST)
    public DeleteUserCardResponse editUserCardsInfo(@RequestBody DeleteUserCardRequest request) {
        DeleteUserCardResponse response = new DeleteUserCardResponse();
        try {
            szjUserCardInfoService.deleteCardInfo(request);
            response.setSuccess(true);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }
}
