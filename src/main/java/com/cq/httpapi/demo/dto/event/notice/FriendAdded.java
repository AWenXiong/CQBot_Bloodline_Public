package com.cq.httpapi.demo.dto.event.notice;

import com.cq.httpapi.demo.dto.event.Event;

public class FriendAdded extends Event {

    private String notice_type;
    private Long user_id;

    public String getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(String notice_type) {
        this.notice_type = notice_type;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "FriendAdded{" +
                "notice_type='" + notice_type + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
