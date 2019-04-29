package com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest;

public class AddUserCardsEditingRequest {

    public String Openid;
    public Long GroupId;
    public Long CardInfoId;
    public Double OriginLevel;
    public Double OriginPosition;

    public String getOpenid() {
        return Openid;
    }

    public void setOpenid(String openid) {
        Openid = openid;
    }

    public Long getGroupId() {
        return GroupId;
    }

    public void setGroupId(Long groupId) {
        GroupId = groupId;
    }

    public Long getCardInfoId() {
        return CardInfoId;
    }

    public void setCardInfoId(Long cardInfoId) {
        CardInfoId = cardInfoId;
    }

    public Double getOriginLevel() {
        return OriginLevel;
    }

    public void setOriginLevel(Double originLevel) {
        OriginLevel = originLevel;
    }

    public Double getOriginPosition() {
        return OriginPosition;
    }

    public void setOriginPosition(Double originPosition) {
        OriginPosition = originPosition;
    }
}
