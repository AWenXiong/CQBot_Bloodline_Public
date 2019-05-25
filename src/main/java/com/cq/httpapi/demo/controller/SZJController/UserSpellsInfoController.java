package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.DeleteUserSpellRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.EditUserSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.GetUserSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse.DeleteUserSpellResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse.EditUserSpellsInfoResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse.GetUserSpellsInfoResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse.GetUserSpellsInfoResponseData;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.service.SZJService.SZJUserSpellInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@Api(tags = "用户法阵信息控制器")
@RestController
@RequestMapping(value = "/szj")
public class UserSpellsInfoController {

    @Resource
    private SZJUserSpellInfoService szjUserSpellInfoService;

    @ApiOperation(value = "获取用户法阵信息")
    @RequestMapping(value = "/GetUserSpellsInfo", method = RequestMethod.POST)
    public GetUserSpellsInfoResponse getUserSpellsInfo(@RequestBody GetUserSpellsInfoRequest request) {
        GetUserSpellsInfoResponse response = new GetUserSpellsInfoResponse();
        try {
            ArrayList<GetUserSpellsInfoResponseData> data = szjUserSpellInfoService.getSpellsInfo(request);
            response.setSuccess(true);
            response.setData(data);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    @ApiOperation(value = "新增/编辑用户法阵")
    @RequestMapping(value = "/EditUserSpellsInfo", method = RequestMethod.POST)
    public EditUserSpellsInfoResponse editUserSpellsInfo(@RequestBody EditUserSpellsInfoRequest request) {
        EditUserSpellsInfoResponse response = new EditUserSpellsInfoResponse();
        try {
            szjUserSpellInfoService.updateSpellsInfo(request);
            response.setSuccess(true);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    @ApiOperation(value = "用户法阵删除")
    @RequestMapping(value = "/DeleteUserSpell", method = RequestMethod.POST)
    public DeleteUserSpellResponse deleteUserSpellInfo(@RequestBody DeleteUserSpellRequest request) {
        DeleteUserSpellResponse response = new DeleteUserSpellResponse();
        try {
            szjUserSpellInfoService.deleteUserSpell(request);
            response.setSuccess(true);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }
}
