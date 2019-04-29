package com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest;

import java.util.ArrayList;

public class EditUserQueueCardRequest {

    public String Openid;
    public Long Id;
    public ArrayList<EditUserQueueCardRequestData> UserQueueCard;

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

    public ArrayList<EditUserQueueCardRequestData> getUserQueueCard() {
        return UserQueueCard;
    }

    public void setUserQueueCard(ArrayList<EditUserQueueCardRequestData> userQueueCard) {
        UserQueueCard = userQueueCard;
    }
}
