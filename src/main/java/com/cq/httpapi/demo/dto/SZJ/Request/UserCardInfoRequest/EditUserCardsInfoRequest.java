package com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest;

import java.util.ArrayList;

public class EditUserCardsInfoRequest {

    public String Openid;
    public Long Id;
    public ArrayList<EditUserCardsInfoData> UserCardInfo;

    public String getOpenid() {
        return Openid;
    }

    public void setOpenid(String openid) {
        Openid = openid;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public ArrayList<EditUserCardsInfoData> getUserCardInfo() {
        return UserCardInfo;
    }

    public void setUserCardInfo(ArrayList<EditUserCardsInfoData> userCardInfo) {
        UserCardInfo = userCardInfo;
    }
}
