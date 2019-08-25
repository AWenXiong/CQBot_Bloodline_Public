package com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest;

public class CreateEnemyInfoRequestLevelCard {

    public Long CardInfoId;
    public Long IsLeader;

    public Long getIsLeader() {
        return IsLeader;
    }

    public void setIsLeader(Long isLeader) {
        IsLeader = isLeader;
    }

    public Long getCardInfoId() {

        return CardInfoId;
    }

    public void setCardInfoId(Long cardInfoId) {
        CardInfoId = cardInfoId;
    }
}
