package com.cq.httpapi.demo.myhandler;

import com.cq.httpapi.demo.dto.send.SendGroupMessage;
import com.cq.httpapi.demo.handler.httphandler.notice.GrpMembChangeHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.notice.GrpNoticeHttpHandler;
import com.cq.httpapi.demo.kit.TimeKit;

public class MemberChangedHandler {

    public static void MemberQuitHandler(GrpNoticeHttpHandler grpNoticeHttpHandler) {
        try {
            if (GrpMembChangeHttpReqHandler.class.isInstance(grpNoticeHttpHandler)) {
                GrpMembChangeHttpReqHandler grpMembChangeHttpReqHandler = (GrpMembChangeHttpReqHandler) grpNoticeHttpHandler;
                String noticeType = grpMembChangeHttpReqHandler.getNoticeType();
                String subType = grpMembChangeHttpReqHandler.getSubType();
                String groupId = grpMembChangeHttpReqHandler.getGroupId();
                String userId = grpMembChangeHttpReqHandler.getUserId();

                // 群成员减少
                if (subType.equals("leave")) {
                    SendGroupMessage sendGroupMessage = new SendGroupMessage();
                    String message = "抱歉 " + userId + " 于 " + TimeKit.getFormalTime() + " 退出了该群";
                    sendGroupMessage.setMessage(message);
                    sendGroupMessage.setGroup_id(groupId);
                    sendGroupMessage.execute();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void MemberJointHandler(GrpNoticeHttpHandler grpNoticeHttpHandler) {
        try {
            if (GrpMembChangeHttpReqHandler.class.isInstance(grpNoticeHttpHandler)) {
                GrpMembChangeHttpReqHandler grpMembChangeHttpReqHandler = (GrpMembChangeHttpReqHandler) grpNoticeHttpHandler;
                String noticeType = grpMembChangeHttpReqHandler.getNoticeType();
                String subType = grpMembChangeHttpReqHandler.getSubType();
                String groupId = grpMembChangeHttpReqHandler.getGroupId();
                String userId = grpMembChangeHttpReqHandler.getUserId();

                // 群成员增加
                if (subType.equals("approve")) {
                    SendGroupMessage sendGroupMessage = new SendGroupMessage();
                    String message = "欢迎 " + userId + " 于 " + TimeKit.getFormalTime() + " 加入了该群";
                    sendGroupMessage.setMessage(message);
                    sendGroupMessage.setGroup_id(groupId);
                    sendGroupMessage.execute();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
