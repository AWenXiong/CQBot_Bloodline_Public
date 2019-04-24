package com.cq.httpapi.demo.entity.SZJ;


public class Szjinvitationcode {

    private long id;
    private String type;
    private java.sql.Timestamp dueTime;
    private double effectiveTime;
    private String invitationCode;
    private long extraFrequency;
    private String description;
    private long sortCode;
    private java.sql.Timestamp createOn;
    private String createUserId;
    private String createBy;
    private java.sql.Timestamp modifiedOn;
    private String modifiedUserId;
    private String modifiedBy;

    public long getExtrafrequency() {
        return extraFrequency;
    }

    public void setExtrafrequency(long extrafrequency) {
        this.extraFrequency = extrafrequency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public java.sql.Timestamp getDueTime() {
        return dueTime;
    }

    public void setDueTime(java.sql.Timestamp dueTime) {
        this.dueTime = dueTime;
    }


    public double getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(double effectiveTime) {
        this.effectiveTime = effectiveTime;
    }


    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
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
