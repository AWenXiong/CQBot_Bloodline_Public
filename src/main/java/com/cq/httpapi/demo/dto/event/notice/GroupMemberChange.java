package com.cq.httpapi.demo.dto.event.notice;

import com.cq.httpapi.demo.dto.event.Event;

public class GroupMemberChange extends Event {

    private String notice_type;
    private String sub_type;
    private Long group_id;
    private Long operator_id;
    private Long user_id;

    public String getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(String notice_type) {
        this.notice_type = notice_type;
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

    public Long getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(Long operator_id) {
        this.operator_id = operator_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "GroupMemberChange{" +
                "notice_type='" + notice_type + '\'' +
                ", sub_type='" + sub_type + '\'' +
                ", group_id=" + group_id +
                ", operator_id=" + operator_id +
                ", user_id=" + user_id +
                '}';
    }
}
