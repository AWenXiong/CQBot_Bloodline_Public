package com.cq.httpapi.demo.handler.eventhandler;

import com.cq.httpapi.demo.dto.response.request.GroupAddedRequestResponse;
import com.cq.httpapi.demo.handler.Handler;

import javax.servlet.http.HttpServletRequest;

public class RequestEventHandler implements Handler {

    private HttpServletRequest httpServletRequest;

    public RequestEventHandler() {
    }

    public RequestEventHandler(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * 处理加好友请求
     */
    public void friendAddedRequestHandler() {

    }

    /**
     * 处理加群请求/邀请
     *
     * @return
     */
    public GroupAddedRequestResponse groupAddedRequestHandler() {
        return new GroupAddedRequestResponse();
    }
}
