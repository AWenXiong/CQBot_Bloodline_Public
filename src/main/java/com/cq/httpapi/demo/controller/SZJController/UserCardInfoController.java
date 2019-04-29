package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.DeleteUserCardRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.EditUserCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.GetUserCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserCardInfoResponse.DeleteUserCardResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserCardInfoResponse.EditUserCardsInfoResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserCardInfoResponse.GetUserCardsInfoResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserCardInfoResponse.GetUserCardsInfoResponseData;
import com.cq.httpapi.demo.exception.SZJException.UserCardInfoException.DeleteUserCardException;
import com.cq.httpapi.demo.exception.SZJException.UserCardInfoException.GetUserCardsInfoException;
import com.cq.httpapi.demo.exception.SZJException.UserCardInfoException.UpdateUserCardsInfoExcpetion;
import com.cq.httpapi.demo.service.SZJService.SZJUserCardInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/szj")
public class UserCardInfoController {

    @Resource
    private SZJUserCardInfoService szjUserCardInfoService;

    /**
     * 获取用户卡组的卡牌信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetUserCardsInfo", method = RequestMethod.POST)
    public GetUserCardsInfoResponse getUserCardsInfo(@RequestBody GetUserCardsInfoRequest request) {
        GetUserCardsInfoResponse response = new GetUserCardsInfoResponse();
        try {
            ArrayList<GetUserCardsInfoResponseData> data = szjUserCardInfoService.getCardInfo(request);
            response.setSuccess(true);
            response.setData(data);
        } catch (GetUserCardsInfoException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
        }
        return response;
    }

    /**
     * 新增/编辑用户卡组的卡牌
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/EditUserCardsInfo", method = RequestMethod.POST)
    public EditUserCardsInfoResponse editUserCardsInfo(@RequestBody EditUserCardsInfoRequest request) {
        EditUserCardsInfoResponse response = new EditUserCardsInfoResponse();
        try {
            szjUserCardInfoService.updateCardInfo(request);
            response.setSuccess(true);
        } catch (UpdateUserCardsInfoExcpetion e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
        }
        return response;
    }

    /**
     * 用户卡牌删除(逻辑删除)
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/DeleteUserCard", method = RequestMethod.POST)
    public DeleteUserCardResponse editUserCardsInfo(@RequestBody DeleteUserCardRequest request) {
        DeleteUserCardResponse response = new DeleteUserCardResponse();
        try {
            szjUserCardInfoService.deleteCardInfo(request);
            response.setSuccess(true);
        } catch (DeleteUserCardException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
        }
        return response;
    }
}
