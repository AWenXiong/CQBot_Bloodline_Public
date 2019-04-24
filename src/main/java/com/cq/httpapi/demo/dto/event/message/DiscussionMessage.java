package com.cq.httpapi.demo.dto.event.message;

import com.cq.httpapi.demo.dto.event.Event;
import com.cq.httpapi.demo.entity.DiscussionSender;
import com.cq.httpapi.demo.entity.Message;

public class DiscussionMessage extends Event {

    private String message_type;
    private Long message_id;
    private Long disscuss_id;
    private Long user_id;
    private Message message;
    private String raw_string;
    private Long font;
    private DiscussionSender discussionSender;


}
