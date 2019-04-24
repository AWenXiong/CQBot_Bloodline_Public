package com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest;

public class GetAllCardsOtherNameRequest {

    public String Openid;

    @Override
    public String toString() {
        return "GetAllCardsOtherNameRequest{" +
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
