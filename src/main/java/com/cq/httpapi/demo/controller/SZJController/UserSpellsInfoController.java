package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.DeleteUserSpellRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.EditUserSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.GetUserSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse.DeleteUserSpellResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse.EditUserSpellsInfoResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse.GetUserSpellsInfoResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse.GetUserSpellsInfoResponseData;
import com.cq.httpapi.demo.exception.SZJException.UserSpellInfoException.DeleteUserSpellException;
import com.cq.httpapi.demo.exception.SZJException.UserSpellInfoException.EditUserSpellsInfoException;
import com.cq.httpapi.demo.exception.SZJException.UserSpellInfoException.GetUserSpellsInfoException;
import com.cq.httpapi.demo.service.SZJService.SZJUserSpellInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/szj")
public class UserSpellsInfoController {

    @Resource
    private SZJUserSpellInfoService szjUserSpellInfoService;

    /**
     * 获取用户法阵信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetUserSpellsInfo", method = RequestMethod.POST)
    public GetUserSpellsInfoResponse getUserSpellsInfo(@RequestBody GetUserSpellsInfoRequest request) {
        GetUserSpellsInfoResponse response = new GetUserSpellsInfoResponse();
        try {
            ArrayList<GetUserSpellsInfoResponseData> data = szjUserSpellInfoService.getSpellsInfo(request);
            response.setSuccess(true);
            response.setData(data);
        } catch (GetUserSpellsInfoException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
        }
        return response;
    }

    /**
     * 新增/编辑用户法阵
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/EditUserSpellsInfo", method = RequestMethod.POST)
    public EditUserSpellsInfoResponse editUserSpellsInfo(@RequestBody EditUserSpellsInfoRequest request) {
        EditUserSpellsInfoResponse response = new EditUserSpellsInfoResponse();
        try {
            szjUserSpellInfoService.updateSpellsInfo(request);
            response.setSuccess(true);
        } catch (EditUserSpellsInfoException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
        }
        return response;
    }

    /**
     * 用户法阵删除(逻辑删除)
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/DeleteUserSpell", method = RequestMethod.POST)
    public DeleteUserSpellResponse deleteUserSpellInfo(@RequestBody DeleteUserSpellRequest request) {
        DeleteUserSpellResponse response = new DeleteUserSpellResponse();
        try {
            szjUserSpellInfoService.deleteUserSpell(request);
            response.setSuccess(true);
        } catch (DeleteUserSpellException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
        }
        return response;
    }
}
