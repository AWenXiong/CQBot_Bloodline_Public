package com.cq.httpapi.demo.dto.SZJ.Request.UserGroupInfoRequest;

public class GetUserCardsInfoRequest {

    public String Openid;

    @Override
    public String toString() {
        return "GetUserCardsInfoRequest{" +
                "Openid='" + Openid + '\'' +
                '}';
    }

    public String getOpenid() {
        return Openid;
    }

    public void setOpenid(String openid) {
        Openid = openid;
    }
}
