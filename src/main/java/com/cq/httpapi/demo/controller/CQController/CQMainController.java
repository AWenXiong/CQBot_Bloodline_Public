package com.cq.httpapi.demo.controller.CQController;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.User;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.handler.eventhandler.RequestEventHandler;
import com.cq.httpapi.demo.handler.httphandler.CQHttpHandler;
import com.cq.httpapi.demo.handler.httphandler.message.GrpMsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.MsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.PriMsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.notice.GrpMembChangeHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.notice.GrpNoticeHttpHandler;
import com.cq.httpapi.demo.kit.CQKit.CQSenderKit;
import com.cq.httpapi.demo.myhandler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@ApiIgnore
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
    @Resource
    private ItemHandler itemHandler;
    @Resource
    private ImportHandler importHandler;
    @Resource
    private PartnerHandler partnerHandler;
    @Resource
    private STZBSkillHandler stzbSkillHandler;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

                System.err.println(body);
                //=================================================================================

                switch (message_type) {
                    // 若消息为群组消息
                    case "group": {

                        GrpMsgHttpReqHandler grpMsgHttpReqHandler = new GrpMsgHttpReqHandler(header, body);
                        String guild = grpMsgHttpReqHandler.getGroupId();
                        String user = grpMsgHttpReqHandler.getUserId();

                        if (!guild.equals("192870189") && !guild.equals("471572605") && !guild.equals("717123368")) {

                            if (purchaseHandler.checkService(guild, "tower")) {
                                // 问答
                                allResponse.add(towerHandler.answerQuestion(grpMsgHttpReqHandler));
                                allResponse.add(towerHandler.towerManager(grpMsgHttpReqHandler));
                            }

                            if (purchaseHandler.checkService(guild, "card")) {
                                // 物品
                                allResponse.add(itemHandler.itemChecker(grpMsgHttpReqHandler));
                                // 技能
                                allResponse.add(cardHandler.cardChecker2(grpMsgHttpReqHandler));
                                // 好感度
                                allResponse.add(cardHandler.cardChecker(grpMsgHttpReqHandler));
                                // 命运链接伙伴
                                allResponse.add(partnerHandler.getMaster(grpMsgHttpReqHandler));
                                // 查极限
                                allResponse.add(cardHandler.maxCardChecker(grpMsgHttpReqHandler));
                            }

                            // 卡牌信息管理
                            allResponse.add(cardHandler.cardManager(grpMsgHttpReqHandler));

                        }

                        // 游戏道具信息管理
                        if (user.equals(User.DOLLYBELU.getUserId())) {
                            allResponse.add(itemHandler.itemManager(grpMsgHttpReqHandler));
                        }

                        // 禁言
                        if (purchaseHandler.checkService(guild, "ban")) {
                            allResponse.add(BanHandler.banHandler(grpMsgHttpReqHandler));
                        }

                        // 定时提醒
                        if (purchaseHandler.checkService(guild, "remind")) {
                            allResponse.add(remindHandler.setRemind(grpMsgHttpReqHandler));
                            allResponse.add(remindHandler.getRemind(grpMsgHttpReqHandler));
                            allResponse.add(remindHandler.deleteRemind(grpMsgHttpReqHandler));
                            allResponse.add(remindHandler.updateRemind(grpMsgHttpReqHandler));
                        }

                        // 查看服务
                        allResponse.add(purchaseHandler.getOwnPurchase(grpMsgHttpReqHandler));

                        // 率土之滨
                        if (purchaseHandler.checkService(guild, "stzb")) {
                            allResponse.add(stzbSkillHandler.skillChecker(grpMsgHttpReqHandler));
                        }
                        break;
                    }

                    // 若消息为私人消息
                    case "private": {

                        PriMsgHttpReqHandler priMsgHttpReqHandler = new PriMsgHttpReqHandler(header, body);
                        String userId = CQSenderKit.GetMsgSenderId(priMsgHttpReqHandler);

                        // 回答问题
                        allResponse.add(towerHandler.answerQuestion(priMsgHttpReqHandler));
                        // 查极限
                        allResponse.add(cardHandler.maxCardChecker(priMsgHttpReqHandler));

                        // 问答管理
                        if (purchaseHandler.checkService("p" + userId, "tower")) {
                            allResponse.add(towerHandler.towerManager(priMsgHttpReqHandler));
                        }

                        if (userId.equals(User.DOLLYBELU.getUserId())) {
                            // 启动、停止、重启提醒
                            allResponse.add(remindHandler.startCheckSchedule(priMsgHttpReqHandler));
                            allResponse.add(remindHandler.stopCheckSchedule(priMsgHttpReqHandler));
                            allResponse.add(remindHandler.restartCheckSchedule(priMsgHttpReqHandler));

                            // 服务管理
                            allResponse.add(purchaseHandler.addPurchase(priMsgHttpReqHandler));
                            allResponse.add(purchaseHandler.deletePurchase(priMsgHttpReqHandler));
                            allResponse.add(purchaseHandler.SAGetPurchaseByGuild(priMsgHttpReqHandler));
                            allResponse.add(purchaseHandler.getOwnPurchase(priMsgHttpReqHandler));

                            // 发送私聊或群信息
                            allResponse.add(SendMessageHandler.sendGroupMessage(priMsgHttpReqHandler));
                            allResponse.add(SendMessageHandler.sendPrivateMessage(priMsgHttpReqHandler));

                            // 导入极限图
                            allResponse.add(importHandler.importMaxData(priMsgHttpReqHandler));
                            // 同步命运伙伴信息
                            allResponse.add(partnerHandler.sync(priMsgHttpReqHandler));

                            // 添加服务
                            allResponse.add(purchaseHandler.getOwnPurchase(priMsgHttpReqHandler));

                            // 物品管理
                            allResponse.add(itemHandler.itemManager(priMsgHttpReqHandler));
                        }

                        // 好感度
                        allResponse.add(cardHandler.cardChecker(priMsgHttpReqHandler));
                        allResponse.add(cardHandler.cardManager(priMsgHttpReqHandler));
                        allResponse.add(cardHandler.cardChecker2(priMsgHttpReqHandler));

                        // 物品
                        allResponse.add(itemHandler.itemChecker(priMsgHttpReqHandler));

                        // 逆向查询命运伙伴
                        allResponse.add(partnerHandler.getMaster(priMsgHttpReqHandler));

                        break;
                    }
                }

//            // import
//            allResponse.add(ImportHandler.importHandler(reqHandler, cardService));
                //=================================================================================

                try {
                    // 遍历响应列表，若flag为真则返回该条响应
                    // 并记录触发响应的消息以及回复的消息
                    for (Response response : allResponse) {
                        if (response != null) {
                            if (response.isFlag()) {
                                MsgHttpReqHandler handler = null;
                                switch (message_type) {
                                    case "group": {
                                        handler = new GrpMsgHttpReqHandler(header, body);
                                        break;
                                    }
                                    case "private": {
                                        handler = new PriMsgHttpReqHandler(header, body);
                                        break;
                                    }
                                }
                                logger.info(handler.toString());
                                logger.warn(handler.getMessage() + " " + handler.getUserId());
                                logger.info(response.toString());
                                logger.warn(response.toString());
                                return response;
                            }
                            if (response.isOpFlag()) {
                                response.execute();
                            }
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
//                        System.err.println(body);
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
