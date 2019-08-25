package com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest;

import java.util.ArrayList;

public class BatchAddUserCardsRequest {

    public String Openid;
    public Long GroupId;
    public ArrayList<BatchAddUserCardsData> UserCards;

    public String getOpenId() {
        return Openid;
    }

    public void setOpenId(String openId) {
        Openid = openId;
    }

    public Long getGroupId() {
        return GroupId;
    }

    public void setGroupId(Long groupId) {
        GroupId = groupId;
    }

    public ArrayList<BatchAddUserCardsData> getUserCards() {
        return UserCards;
    }

    public void setUserCards(ArrayList<BatchAddUserCardsData> userCards) {
        UserCards = userCards;
    }
}
