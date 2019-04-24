package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.UserGroupInfoRequest.DeleteUserCardRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserGroupInfoRequest.EditUserCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserGroupInfoRequest.GetUserCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserGroupInfoResponse.DeleteUserCardResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserGroupInfoResponse.EditUserCardsInfoResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserGroupInfoResponse.GetUserCardsInfoResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/szj")
public class UserGroupInfoController {

    /**
     * 获取用户卡组的卡牌信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetUserCardsInfo", method = RequestMethod.POST)
    public GetUserCardsInfoResponse getUserCardsInfo(@RequestBody GetUserCardsInfoRequest request) {
        return null;
    }

    /**
     * 新增/编辑用户卡组的卡牌
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/EditUserCardsInfo", method = RequestMethod.POST)
    public EditUserCardsInfoResponse editUserCardsInfo(@RequestBody EditUserCardsInfoRequest request) {
        return null;
    }

    /**
     * 用户卡牌删除(逻辑删除)
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/DeleteUserCard", method = RequestMethod.POST)
    public DeleteUserCardResponse editUserCardsInfo(@RequestBody DeleteUserCardRequest request) {
        return null;
    }
}
