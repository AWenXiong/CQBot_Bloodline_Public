package com.cq.httpapi.demo.entity.SZJ;


import java.io.Serializable;

public class Szjcardinfoothername implements Serializable {

    private long id;
    private long cardInfoId;
    private String name;
    private String description;
    private long deletionStateCode;
    private long sortCode;
    private java.sql.Timestamp createOn;
    private String createUserId;
    private String createBy;
    private java.sql.Timestamp modifiedOn;
    private String modifiedUserId;
    private String modifiedBy;

    public long getDeletionStateCode() {
        return deletionStateCode;
    }

    public void setDeletionStateCode(long deletionStateCode) {
        this.deletionStateCode = deletionStateCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getCardInfoId() {
        return cardInfoId;
    }

    public void setCardInfoId(long cardInfoId) {
        this.cardInfoId = cardInfoId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
