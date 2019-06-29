package com.cq.httpapi.demo.kit.CQKit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.handler.httphandler.message.GrpMsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.MsgHttpReqHandler;

public class CQSenderKit {

    public static String GetGrpMsgSenderRole(GrpMsgHttpReqHandler grpMsgHttpReqHandler) {
        JSONObject sender = JSON.parseObject(grpMsgHttpReqHandler.getSender().toString());
        return sender.get("role").toString();
    }

    public static boolean CheckGrpMsgSenderRole(GrpMsgHttpReqHandler grpMsgHttpReqHandler, String role) {
        String senderRole = GetGrpMsgSenderRole(grpMsgHttpReqHandler);
        if (senderRole.equals(role)) {
            return true;
        } else {
            return false;
        }
    }

    public static String GetGrpMsgSenderId(GrpMsgHttpReqHandler grpMsgHttpReqHandler) {
        JSONObject sender = JSON.parseObject(grpMsgHttpReqHandler.getSender().toString());
        return sender.get("user_id").toString();
    }

    public static String GetMsgSenderId(MsgHttpReqHandler msgHttpReqHandler) {
        JSONObject sender = JSON.parseObject(msgHttpReqHandler.getSender().toString());
        return sender.get("user_id").toString();
    }

    public static boolean CheckGrpMsgSenderId(GrpMsgHttpReqHandler grpMsgHttpReqHandler, String id) {
        if (GetGrpMsgSenderId(grpMsgHttpReqHandler).equals(id)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean CheckMsgSenderId(MsgHttpReqHandler msgHttpReqHandler, String id) {
        if (GetMsgSenderId(msgHttpReqHandler).equals(id)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isAdminOrOwner(GrpMsgHttpReqHandler grpMsgHttpReqHandler) {
        return CQSenderKit.CheckGrpMsgSenderRole(grpMsgHttpReqHandler, "admin") ||
                CQSenderKit.CheckGrpMsgSenderRole(grpMsgHttpReqHandler, "owner");
    }

}
