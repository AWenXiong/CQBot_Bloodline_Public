package com.cq.httpapi.demo.dto.SZJ.Request.CardGroupRequest;

public class GetAllGroupsRequest {

    public String Openid;

    @Override
    public String toString() {
        return "GetAllGroupsRequest{" +
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
