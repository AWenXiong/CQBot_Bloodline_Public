package com.cq.httpapi.demo.dto.event.message;

import com.cq.httpapi.demo.dto.event.Event;
import com.cq.httpapi.demo.entity.Anonymous;
import com.cq.httpapi.demo.entity.GroupMessageSender;
import com.cq.httpapi.demo.entity.Message;

public class GroupMessage extends Event {

    private String message_type;
    private String sub_type;
    private Long message_id;
    private Long group_id;
    private Long user_id;
    private Anonymous anonymous;
    private Message message;
    private String raw_message;
    private Long font;
    private GroupMessageSender sender;

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public Long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
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

    public Anonymous getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Anonymous anonymous) {
        this.anonymous = anonymous;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getRaw_message() {
        return raw_message;
    }

    public void setRaw_message(String raw_message) {
        this.raw_message = raw_message;
    }

    public Long getFont() {
        return font;
    }

    public void setFont(Long font) {
        this.font = font;
    }

    public GroupMessageSender getSender() {
        return this.sender;
    }

    public void setSender(GroupMessageSender groupMessageSender) {
        this.sender = groupMessageSender;
    }

    @Override
    public String toString() {
        return "GroupMessage{" +
                "message_type='" + message_type + '\'' +
                ", sub_type='" + sub_type + '\'' +
                ", message_id=" + message_id +
                ", group_id=" + group_id +
                ", user_id=" + user_id +
                ", anonymous=" + anonymous +
                ", message=" + message +
                ", raw_message='" + raw_message + '\'' +
                ", font=" + font +
                ", sender=" + sender +
                '}';
    }
}
