package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest.GetUserInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest.UserLoginRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest.UserRegisterRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserLoginResponse.*;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.exception.SZJException.UserLoginException.GetUserInfoException;
import com.cq.httpapi.demo.exception.SZJException.UserLoginException.UserNotExistException;
import com.cq.httpapi.demo.exception.SZJException.UserLoginException.UserRegisterException;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "用户控制器")
@RestController
@RequestMapping(value = "/szj")
public class UserLoginController {

    @Resource
    private SZJUserInfoService szjUserInfoService;

    /**
     * 用户注册
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "注册")
    @RequestMapping(value = "/UserRegister", method = RequestMethod.POST)
    public UserRegisterResponse register(@RequestBody UserRegisterRequest request) {
        UserRegisterResponse response = new UserRegisterResponse();
        try {
            // 新增记录并获取新纪录的主键值
            Long id = szjUserInfoService.regist(request);
            // 根据主键值获取记录
            Szjuserinfo szjuserinfo = szjUserInfoService.getById(id);
            // 根据获取到的用户记录构造userInfo
            UserRegisterResponseUserInfo userInfo = new UserRegisterResponseUserInfo(szjuserinfo);
            // 设置回复
            response.setSuccess(true);
            response.setUserInfo(userInfo);
        } catch (SZJException e) { // 注册错误（某些字段为空）或用户已存在
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "登录")
    @RequestMapping(value = "/UserLogOn", method = RequestMethod.POST)
    public UserLoginResponse login(@RequestBody UserLoginRequest request) {
        UserLoginResponse response = new UserLoginResponse();
        try {
            // 根据请求进行登录并获取相关数据
            UserLoginResponseData data = szjUserInfoService.login(request);
            // 设置回复
            response.setSuccess(true);
            response.setData(data);
        } catch (SZJException e) { // 用户不存在
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "/GetUserInfo", method = RequestMethod.POST)
    public GetUserInfoResponse getUserInfo(@RequestBody GetUserInfoRequest request) {
        GetUserInfoResponse response = new GetUserInfoResponse();
        try {
            Szjuserinfo szjuserinfo = szjUserInfoService.getUserInfo(request);
            GetUserInfoResponseData data = new GetUserInfoResponseData(szjuserinfo);
            // 设置回复
            response.setSuccess(true);
            response.setData(data);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }
}
