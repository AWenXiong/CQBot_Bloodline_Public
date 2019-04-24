package com.cq.httpapi.demo.handler.eventhandler;

import com.cq.httpapi.demo.handler.Handler;

import javax.servlet.http.HttpServletRequest;

public class NoticeEventHandler implements Handler {

    private HttpServletRequest httpServletRequest;

    public NoticeEventHandler() {
    }

    public NoticeEventHandler(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * 群管理员变动通知
     */
    public void groupAdminChangeHandler() {

    }

    /**
     * 处理群成员增加通知
     */
    public void groupMemberChangeHandler() {

    }

    /**
     * 处理新增好友通知
     */
    public void friendAddedHandler() {

    }

}
