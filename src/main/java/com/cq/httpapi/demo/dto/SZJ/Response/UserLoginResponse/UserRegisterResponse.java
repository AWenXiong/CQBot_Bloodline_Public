package com.cq.httpapi.demo.dto.SZJ.Response.UserLoginResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

public class UserRegisterResponse extends SZJResponse {

    public UserRegisterResponseUserInfo UserInfo;

    public UserRegisterResponseUserInfo getUserInfo() {
        return UserInfo;
    }

    public void setUserInfo(UserRegisterResponseUserInfo userInfo) {
        UserInfo = userInfo;
    }
}
