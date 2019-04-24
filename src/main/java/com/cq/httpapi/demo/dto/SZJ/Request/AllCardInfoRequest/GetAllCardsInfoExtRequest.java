package com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest;

public class GetAllCardsInfoExtRequest {

    public String Openid;

    @Override
    public String toString() {
        return "GetAllCardsInfoExtRequest{" +
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
