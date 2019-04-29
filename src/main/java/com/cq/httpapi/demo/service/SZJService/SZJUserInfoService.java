package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest.GetUserInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest.UserLoginRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest.UserRegisterRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserLoginResponse.UserLoginResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;

public interface SZJUserInfoService {

    long regist(UserRegisterRequest request);

    UserLoginResponseData login(UserLoginRequest request);

    Szjuserinfo getUserInfo(GetUserInfoRequest request);

    Szjuserinfo getById(Long id);

    Szjuserinfo getUserInfoByOpenId(String openId);

    boolean existOpenId(String openId);

    boolean existCode(String userName);

    long delete(UserRegisterRequest userRegisterRequest);

    long update(UserRegisterRequest userRegisterRequest);
}
