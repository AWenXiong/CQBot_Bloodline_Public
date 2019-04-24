package com.cq.httpapi.demo.entity;


public class Tower {

    private long id;
    private String question;
    private String answer;
    private String guild;
    private long usable;
    private String description;
    private long deletionStateCode;
    private java.sql.Timestamp createTime;
    private String createUserId;
    private java.sql.Timestamp modifiedTime;
    private String modifiedUserId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
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


    public long getDeletionStateCode() {
        return deletionStateCode;
    }

    public void setDeletionStateCode(long deletionStateCode) {
        this.deletionStateCode = deletionStateCode;
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

}
