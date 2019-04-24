package com.cq.httpapi.demo.entity.SZJ;


public class Szjuserinfo {
    private long id;
    private String code;
    private String name;
    private String password;
    private String openid;
    private String wechatOpenid;
    private String mobile;
    private String email;
    private long freeQueueTime;
    private String type;
    private String description;
    private long deletionStateCode;
    private long sortCode;
    private java.sql.Timestamp createOn;
    private String createUserId;
    private String createBy;
    private java.sql.Timestamp modifiedOn;
    private String modifiedUserId;
    private String modifiedBy;

    @Override
    public String toString() {
        return "Szjuserinfo{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", openid='" + openid + '\'' +
                ", wechatOpenid='" + wechatOpenid + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", freeQueueTime=" + freeQueueTime +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", deletionStateCode=" + deletionStateCode +
                ", sortCode=" + sortCode +
                ", createOn=" + createOn +
                ", createUserId='" + createUserId + '\'' +
                ", createBy='" + createBy + '\'' +
                ", modifiedOn=" + modifiedOn +
                ", modifiedUserId='" + modifiedUserId + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }


    public String getWechatOpenid() {
        return wechatOpenid;
    }

    public void setWechatOpenid(String wechatOpenid) {
        this.wechatOpenid = wechatOpenid;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public long getFreeQueueTime() {
        return freeQueueTime;
    }

    public void setFreeQueueTime(long freeQueueTime) {
        this.freeQueueTime = freeQueueTime;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public long getDeletionStateCode() {
        return deletionStateCode;
    }

    public void setDeletionStateCode(long deletionStateCode) {
        this.deletionStateCode = deletionStateCode;
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
