package com.cq.httpapi.demo.handler.httphandler.notice;

import java.util.Map;

public interface GrpNoticeHttpHandler {

    Map<String, Object> getHeader();

    Map<String, Object> getRequestMsg();

    String getPostType();

    String getNoticeType();

    String getGroupId();

    String getUserId();

}
