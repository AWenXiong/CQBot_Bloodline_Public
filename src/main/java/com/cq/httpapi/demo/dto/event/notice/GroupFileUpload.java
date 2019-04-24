package com.cq.httpapi.demo.dto.event.notice;

import com.cq.httpapi.demo.dto.event.Event;

public class GroupFileUpload extends Event {

    private String notice_type;
    private Long group_id;
    private Long user_id;
    private Object file;

    public String getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(String notice_type) {
        this.notice_type = notice_type;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Object getFile() {
        return file;
    }

    public void setFile(Object file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "GroupFileUpload{" +
                "notice_type='" + notice_type + '\'' +
                ", group_id=" + group_id +
                ", user_id=" + user_id +
                ", file=" + file +
                '}';
    }
}
