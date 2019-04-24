package com.cq.httpapi.demo.dto.event.request;

import com.cq.httpapi.demo.dto.event.Event;

public class FriendAddedRequest extends Event {

    private String request_type;
    private Long user_id;
    private String comment;
    private String flag;

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
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
        return "FriendAddedRequest{" +
                "request_type='" + request_type + '\'' +
                ", user_id=" + user_id +
                ", comment='" + comment + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
