package com.cq.httpapi.demo.myhandler;

import com.cq.httpapi.demo.annotation.cqannotation.CQResponse;
import com.cq.httpapi.demo.dto.User;
import com.cq.httpapi.demo.dto.response.message.MessageResponse;
import com.cq.httpapi.demo.entity.CQ.Card;
import com.cq.httpapi.demo.handler.httphandler.message.MsgHttpReqHandler;
import com.cq.httpapi.demo.kit.CQKit.CQSenderKit;
import com.cq.httpapi.demo.kit.ObjectKit;
import com.cq.httpapi.demo.service.CQService.PartnerService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

@Component
public class PartnerHandler {

    @Resource
    private PartnerService partnerService;

    @CQResponse
    public MessageResponse getMaster(MsgHttpReqHandler msgHttpReqHandler) {
        String message = msgHttpReqHandler.getMessage();
        MessageResponse response = ObjectKit.getCQMessageResponse(msgHttpReqHandler);

        String masterFlag = "谁命运要";
        if (message.startsWith(masterFlag)) {
            StringBuilder sb = new StringBuilder(message);
            String nickname = sb.substring(masterFlag.length());
            ArrayList<Card> cards = partnerService.getMasters("/" + nickname + "/");
            StringBuilder resSb = new StringBuilder();
            for (Card card : cards) {
                resSb.append(", " + card.getFullname());
            }
            resSb.delete(0, 2);
            response.setFlag(true);
            response.setReply("命运需要 " + nickname + " 的有：\n  " + resSb.toString());
        }

        String syncFlag = "同步命运信息";
        if (message.equals(syncFlag)) {
            if (partnerService.sync(msgHttpReqHandler.getUserId())) {
                response.setFlag(true);
                response.setReply("同步命运伙伴信息成功");
            } else {
                response.setFlag(false);
                response.setReply("同步命运伙伴信息失败");
            }
        }
        return response;
    }

    @CQResponse
    public MessageResponse sync(MsgHttpReqHandler msgHttpReqHandler) {
        String message = msgHttpReqHandler.getMessage();
        MessageResponse response = ObjectKit.getCQMessageResponse(msgHttpReqHandler);
        String syncFlag = "同步命运信息";
        if (message.equals(syncFlag) && CQSenderKit.CheckMsgSenderId(msgHttpReqHandler, User.DOLLYBELU.getUserId())) {
            if (partnerService.sync(msgHttpReqHandler.getUserId())) {
                response.setFlag(true);
                response.setReply("同步命运伙伴信息成功");
            } else {
                response.setFlag(false);
                response.setReply("同步命运伙伴信息失败");
            }
        }
        return response;
    }
}
