package com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest;

public class GetUserInfoRequest {

    public String Openid;

    public String getOpenid() {
        return Openid;
    }

    public void setOpenid(String openid) {
        Openid = openid;
    }

    @Override
    public String toString() {
        return "GetUserInfoRequest{" +
                "Openid='" + Openid + '\'' +
                '}';
    }
}
