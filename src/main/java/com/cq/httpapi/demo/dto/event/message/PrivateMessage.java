package com.cq.httpapi.demo.dto.event.message;

import com.cq.httpapi.demo.dto.event.Event;
import com.cq.httpapi.demo.entity.CQ.Message;
import com.cq.httpapi.demo.entity.CQ.PrivateMessageSender;

public class PrivateMessage extends Event {

    private String message_type;
    private String sub_type;
    private Long message_id;
    private Long user_id;
    private Message message;
    private String raw_message;
    private Long font;
    private PrivateMessageSender sender;

    @Override
    public String toString() {
        return "PrivateMessage{" +
                "message_type='" + message_type + '\'' +
                ", sub_type='" + sub_type + '\'' +
                ", message_id=" + message_id +
                ", user_id=" + user_id +
                ", message=" + message +
                ", raw_message='" + raw_message + '\'' +
                ", font=" + font +
                ", privateMessageSender=" + sender +
                '}';
    }

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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public PrivateMessageSender getPrivateMessageSender() {
        return sender;
    }

    public void setPrivateMessageSender(PrivateMessageSender privateMessageSender) {
        this.sender = privateMessageSender;
    }
}
