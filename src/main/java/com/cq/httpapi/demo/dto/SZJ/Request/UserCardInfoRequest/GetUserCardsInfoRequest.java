package com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest;

public class GetUserCardsInfoRequest {

    public String Openid;
    public Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getOpenid() {
        return Openid;
    }

    public void setOpenid(String openid) {
        Openid = openid;
    }
}
