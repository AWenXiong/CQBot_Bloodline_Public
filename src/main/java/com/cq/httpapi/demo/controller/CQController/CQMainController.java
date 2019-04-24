package com.cq.httpapi.demo.controller.CQController;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.User;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.handler.eventhandler.RequestEventHandler;
import com.cq.httpapi.demo.handler.httphandler.CQHttpHandler;
import com.cq.httpapi.demo.handler.httphandler.message.GrpMsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.PriMsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.notice.GrpMembChangeHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.notice.GrpNoticeHttpHandler;
import com.cq.httpapi.demo.kit.SenderKit;
import com.cq.httpapi.demo.myhandler.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class CQMainController {

    @Resource
    private CardHandler cardHandler;
    @Resource
    private TowerHandler towerHandler;
    @Resource
    private PurchaseHandler purchaseHandler;
    @Resource
    private RemindHandler remindHandler;

    @RequestMapping(value = "/deliver")
    @ResponseBody
    public Response deliver(HttpServletRequest httpServletRequest) {
        try {
            JSONObject header = CQHttpHandler.getHeader(httpServletRequest);
            JSONObject body = CQHttpHandler.getBody(httpServletRequest);
            String post_type = body.getString("post_type");


            if (post_type.equals("message")) {

                String message_type = body.getString("message_type");
                ArrayList<Response> allResponse = new ArrayList<>();

//            System.err.println(body);
                //=================================================================================

                switch (message_type) {
                    // 若消息为群组消息
                    case "group": {

                        GrpMsgHttpReqHandler grpMsgHttpReqHandler = new GrpMsgHttpReqHandler(header, body);
                        String guild = grpMsgHttpReqHandler.getGroupId();
                        String user = grpMsgHttpReqHandler.getUserId();

                        // 问答
                        allResponse.add(towerHandler.towerHandler(grpMsgHttpReqHandler));

                        // 好感度
                        allResponse.add(cardHandler.cardChecker(grpMsgHttpReqHandler));
                        allResponse.add(cardHandler.cardHandler(grpMsgHttpReqHandler));

                        // 禁言
                        allResponse.add(BanHandler.banHandler(grpMsgHttpReqHandler));

                        // 定时提醒
                        if (purchaseHandler.checkService(guild, "remind")) {
                            allResponse.add(remindHandler.setRemind(grpMsgHttpReqHandler));
                            allResponse.add(remindHandler.getRemind(grpMsgHttpReqHandler));
                            allResponse.add(remindHandler.deleteRemind(grpMsgHttpReqHandler));
                        }

                        if (user.equals(User.DOLLYBELU.getUserId())) {
                            // 服务
                            allResponse.add(purchaseHandler.getOwnPurchase(grpMsgHttpReqHandler));
                        }

                        break;
                    }

                    // 若消息为私人消息
                    case "private": {

                        PriMsgHttpReqHandler priMsgHttpReqHandler = new PriMsgHttpReqHandler(header, body);
                        String userId = SenderKit.GetMsgSenderId(priMsgHttpReqHandler);

                        if (purchaseHandler.checkService("p" + userId, "tower")) {
                            // 问答
                            allResponse.add(towerHandler.towerHandler(priMsgHttpReqHandler));
                        }

                        if (userId.equals(User.DOLLYBELU.getUserId())) {
                            // 启动、停止、重启提醒
                            allResponse.add(remindHandler.startCheckSchedule(priMsgHttpReqHandler));
                            allResponse.add(remindHandler.stopCheckSchedule(priMsgHttpReqHandler));
                            allResponse.add(remindHandler.restartCheckSchedule(priMsgHttpReqHandler));

                            allResponse.add(purchaseHandler.addPurchase(priMsgHttpReqHandler));
                            allResponse.add(purchaseHandler.deletePurchase(priMsgHttpReqHandler));
                            allResponse.add(purchaseHandler.SAGetPurchaseByGuild(priMsgHttpReqHandler));
                            allResponse.add(purchaseHandler.getOwnPurchase(priMsgHttpReqHandler));

                            // 更新
                            // 发送更新信息
                            allResponse.add(SendMessageHandler.sendTestMessage(priMsgHttpReqHandler));
                            allResponse.add(SendMessageHandler.sendGroupMessage(priMsgHttpReqHandler));
                            allResponse.add(SendMessageHandler.sendPrivateMessage(priMsgHttpReqHandler));
                        }

                        // 好感度
                        allResponse.add(cardHandler.cardChecker(priMsgHttpReqHandler));
                        allResponse.add(cardHandler.cardHandler(priMsgHttpReqHandler));
                        break;
                    }
                }

//            // import
//            allResponse.add(ImportHandler.importHandler(reqHandler, cardService));

                //=================================================================================
                int count = 0;
                try {
                    for (Response response : allResponse) {
                        if (response != null) {
                            if (response.isFlag()) {
                                return response;
                            }
                            if (response.isOpFlag()) {
                                response.execute();
                            }
                            count++;
                        }
                    }
                } catch (Exception e) {
//                e.printStackTrace();
                }
            }

            /*TODO*/
            else if (post_type.equals("notice")) {
                String noticeType = body.getString("notice_type");

                switch (noticeType) {

                    // 群文件上传
                    case "group_upload": {
                        break;
                    }

                    // 群管理员变动
                    case "group_admin": {
                        break;
                    }

                    // 群成员减少
                    case "group_decrease": {
                        GrpNoticeHttpHandler grpNoticeHttpHandler = new GrpMembChangeHttpReqHandler(header, body);
                        String guild = grpNoticeHttpHandler.getGroupId();
                        if (purchaseHandler.checkService(guild, "member_changed")) {
                            MemberChangedHandler.MemberQuitHandler(grpNoticeHttpHandler);
                        }
                        break;
                    }

                    // 群成员增加
                    case "group_increase": {
                        GrpNoticeHttpHandler grpNoticeHttpHandler = new GrpMembChangeHttpReqHandler(header, body);
                        String guild = grpNoticeHttpHandler.getGroupId();
                        if (purchaseHandler.checkService(guild, "member_changed")) {
                            MemberChangedHandler.MemberJointHandler(grpNoticeHttpHandler);
                        }
                        break;
                    }

                    // 好友添加
                    case "friend_add": {
                        break;
                    }
                }

                return null;
            }

            /*TODO*/
            else if (post_type.equals("request")) {
//            System.err.println("request");
//            System.err.print(body);
                new RequestEventHandler(httpServletRequest);
                return null;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


}
