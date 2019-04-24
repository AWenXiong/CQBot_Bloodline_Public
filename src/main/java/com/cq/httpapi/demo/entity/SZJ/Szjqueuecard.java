package com.cq.httpapi.demo.entity.SZJ;


public class Szjqueuecard {

    private long id;
    private long queueInfoId;
    private double level;
    private long cardInfoId;
    private double position;
    private String description;
    private long sortCode;
    private java.sql.Timestamp createOn;
    private String createUserId;
    private String createBy;
    private java.sql.Timestamp modifiedOn;
    private String modifiedUserId;
    private String modifiedBy;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getQueueInfoId() {
        return queueInfoId;
    }

    public void setQueueInfoId(long queueInfoId) {
        this.queueInfoId = queueInfoId;
    }


    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }


    public long getCardInfoId() {
        return cardInfoId;
    }

    public void setCardInfoId(long cardInfoId) {
        this.cardInfoId = cardInfoId;
    }


    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public long getSortCode() {
        return sortCode;
    }

    public void setSortCode(long sortCode) {
        this.sortCode = sortCode;
    }


    public java.sql.Timestamp getCreateOn() {
        return createOn;
    }

    public void setCreateOn(java.sql.Timestamp createOn) {
        this.createOn = createOn;
    }


    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }


    public java.sql.Timestamp getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(java.sql.Timestamp modifiedOn) {
        this.modifiedOn = modifiedOn;
    }


    public String getModifiedUserId() {
        return modifiedUserId;
    }

    public void setModifiedUserId(String modifiedUserId) {
        this.modifiedUserId = modifiedUserId;
    }


    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
