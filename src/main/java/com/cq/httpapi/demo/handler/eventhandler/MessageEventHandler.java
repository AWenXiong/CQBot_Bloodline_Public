package com.cq.httpapi.demo.handler.eventhandler;

import com.cq.httpapi.demo.dto.response.message.DiscussionMessageResponse;
import com.cq.httpapi.demo.dto.response.message.GroupMessageResponse;
import com.cq.httpapi.demo.dto.response.message.PrivateMessageResponse;
import com.cq.httpapi.demo.dto.response.notice.GroupFileUploadResponse;
import com.cq.httpapi.demo.handler.Handler;

import javax.servlet.http.HttpServletRequest;

public class MessageEventHandler implements Handler {

    private HttpServletRequest httpServletRequest;

    public MessageEventHandler() {
    }

    public MessageEventHandler(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }


    /**
     * 处理私聊消息
     *
     * @return
     */
    public PrivateMessageResponse privateMessageHandler() {
        return new PrivateMessageResponse();
    }


    /**
     * 处理群消息
     *
     * @return
     */
    public GroupMessageResponse groupMessageHandler() {
        return new GroupMessageResponse();
    }

    /**
     * 处理讨论组消息
     *
     * @return
     */
    public DiscussionMessageResponse discussionMessageHandler() {
        return new DiscussionMessageResponse();
    }


    /**
     * 处理群文件上传消息
     *
     * @return
     */
    public GroupFileUploadResponse groupFileUploadHandler() {
        return new GroupFileUploadResponse();
    }

}
