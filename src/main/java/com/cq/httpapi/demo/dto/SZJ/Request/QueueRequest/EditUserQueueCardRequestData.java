package com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest;

public class EditUserQueueCardRequestData {

    public Long Id;
    public Double Level;
    public Long CardInfoId;
    public Double Position;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Double getLevel() {
        return Level;
    }

    public void setLevel(Double level) {
        Level = level;
    }

    public Long getCardInfoId() {
        return CardInfoId;
    }

    public void setCardInfoId(Long cardInfoId) {
        CardInfoId = cardInfoId;
    }

    public Double getPosition() {
        return Position;
    }

    public void setPosition(Double position) {
        Position = position;
    }
}
