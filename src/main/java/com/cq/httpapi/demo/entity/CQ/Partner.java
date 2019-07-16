package com.cq.httpapi.demo.entity.CQ;


public class Partner {

    private long id;
    private long cardId;
    private long partner1;
    private long partner2;
    private long partner3;
    private java.sql.Timestamp createTime;
    private String createUserId;
    private String description;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }


    public long getPartner1() {
        return partner1;
    }

    public void setPartner1(long partner1) {
        this.partner1 = partner1;
    }


    public long getPartner2() {
        return partner2;
    }

    public void setPartner2(long partner2) {
        this.partner2 = partner2;
    }


    public long getPartner3() {
        return partner3;
    }

    public void setPartner3(long partner3) {
        this.partner3 = partner3;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }


    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
