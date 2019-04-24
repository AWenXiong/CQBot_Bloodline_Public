package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.DeleteUserSpellRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.EditUserSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.GetUserSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse.DeleteUserSpellResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse.EditUserSpellsInfoResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse.GetUserSpellsInfoResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/szj")
public class UserSpellsInfoController {

    /**
     * 获取用户法阵信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetUserSpellsInfo", method = RequestMethod.POST)
    public GetUserSpellsInfoResponse getUserSpellsInfo(@RequestBody GetUserSpellsInfoRequest request) {
        return null;
    }

    /**
     * 新增/编辑用户法阵
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/EditUserSpellsInfo", method = RequestMethod.POST)
    public EditUserSpellsInfoResponse editUserSpellsInfo(@RequestBody EditUserSpellsInfoRequest request) {
        return null;
    }

    /**
     * 用户法阵删除(逻辑删除)
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/DeleteUserSpell", method = RequestMethod.POST)
    public DeleteUserSpellResponse deleteUserSpellInfo(@RequestBody DeleteUserSpellRequest request) {
        return null;
    }
}
