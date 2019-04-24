package com.cq.httpapi.demo.dto.event.request;

public class GroupAddedRequest {

    private String request_type;
    private String sub_type;
    private Long group_id;
    private String user_id;
    private String comment;
    private String flag;

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "GroupAddedRequest{" +
                "request_type='" + request_type + '\'' +
                ", sub_type='" + sub_type + '\'' +
                ", group_id=" + group_id +
                ", user_id='" + user_id + '\'' +
                ", comment='" + comment + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
