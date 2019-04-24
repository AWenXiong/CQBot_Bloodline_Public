package com.cq.httpapi.demo.entity.SZJ;


public class Szjuserspellinfo {

    private long id;
    private long userId;
    private long groupId;
    private long spellId;
    private long fightingCapacity;
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


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }


    public long getSpellId() {
        return spellId;
    }

    public void setSpellId(long spellId) {
        this.spellId = spellId;
    }


    public long getFightingCapacity() {
        return fightingCapacity;
    }

    public void setFightingCapacity(long fightingCapacity) {
        this.fightingCapacity = fightingCapacity;
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
