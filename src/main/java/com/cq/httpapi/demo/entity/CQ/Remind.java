package com.cq.httpapi.demo.entity.CQ;


public class Remind {

    private long id;
    private String guild;
    private String message;
    private String remindTime;
    private long usable;
    private String description;
    private java.sql.Timestamp createTime;
    private String createUserId;
    private java.sql.Timestamp modifiedTime;
    private String modifiedUserId;
    private String mode;
    private String timezone;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }


    public long getUsable() {
        return usable;
    }

    public void setUsable(long usable) {
        this.usable = usable;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


    public java.sql.Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(java.sql.Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }


    public String getModifiedUserId() {
        return modifiedUserId;
    }

    public void setModifiedUserId(String modifiedUserId) {
        this.modifiedUserId = modifiedUserId;
    }

    @Override
    public String toString() {
        return "Remind{" +
                "id=" + id +
                ", guild='" + guild + '\'' +
                ", message='" + message + '\'' +
                ", remindTime='" + remindTime + '\'' +
                ", usable=" + usable +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", createUserId='" + createUserId + '\'' +
                ", modifiedTime=" + modifiedTime +
                ", modifiedUserId='" + modifiedUserId + '\'' +
                ", mode='" + mode + '\'' +
                '}';
    }
}
