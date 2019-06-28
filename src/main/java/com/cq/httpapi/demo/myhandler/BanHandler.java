package com.cq.httpapi.demo.myhandler;

import com.cq.httpapi.demo.annotation.cqannotation.CQResponse;
import com.cq.httpapi.demo.dto.send.SetGroupBan;
import com.cq.httpapi.demo.handler.httphandler.message.GrpMsgHttpReqHandler;
import com.cq.httpapi.demo.kit.CQKit.CQSenderKit;

public class BanHandler {

    @CQResponse(ResponseStartFlag = "禁言 [CQ:at,qq=")
    public static SetGroupBan banHandler(GrpMsgHttpReqHandler grpMsgHttpReqHandler) {

        SetGroupBan response = new SetGroupBan();
        String message = grpMsgHttpReqHandler.getMessage();

        String banMessage = "禁言 [CQ:at,qq=";
        String banTimeFlag = "] ";
        if ((CQSenderKit.CheckGrpMsgSenderRole(grpMsgHttpReqHandler, "admin")
                || CQSenderKit.CheckGrpMsgSenderRole(grpMsgHttpReqHandler, "owner"))
                && message.startsWith(banMessage)) {

            response.setGroup_id(grpMsgHttpReqHandler.getGroupId());  //设置被禁言用户所在的群号

            //获得被禁言的用户的QQ号
            int banMemberStartIndex = message.indexOf(banMessage) + banMessage.length();
            int banMemberEndIndex = message.indexOf(banTimeFlag);
            String banMember = message.substring(banMemberStartIndex, banMemberEndIndex).trim();

            response.setUser_id(banMember);  //设置被禁言的用户

            //获得被禁言时长
            int banTimeStartIndex = message.indexOf(banTimeFlag) + banTimeFlag.length();
            String banTime = message.substring(banTimeStartIndex, message.length()).trim();
            if (banTime.endsWith("分钟")) {
                int banDurationEndIndex = banTime.indexOf("分钟");
                Long banDuration = Long.parseLong(banTime.substring(0, banDurationEndIndex)) * 60;
                response.setDuration(banDuration);  //设置禁言时长
            } else if (banTime.endsWith("小时")) {
                int banDurationEndIndex = banTime.indexOf("小时");
                Long banDuration = Long.parseLong(banTime.substring(0, banDurationEndIndex)) * 3600;
                response.setDuration(banDuration);  //设置禁言时长
            } else if (banTime.endsWith("天")) {
                int banDurationEndIndx = banTime.indexOf("天");
                Long banDuration = Long.parseLong(banTime.substring(0, banMemberEndIndex)) * 60 * 60 * 24;
                response.setDuration(banDuration);
            }

        }
        response.setOpFlag(true);
        return response;
    }
}
