package com.cq.httpapi.demo.myhandler;


import com.cq.httpapi.demo.annotation.cqannotation.CQResponse;
import com.cq.httpapi.demo.dto.User;
import com.cq.httpapi.demo.dto.response.message.GroupMessageResponse;
import com.cq.httpapi.demo.dto.response.message.MessageResponse;
import com.cq.httpapi.demo.dto.response.message.PrivateMessageResponse;
import com.cq.httpapi.demo.entity.CQ.Purchase;
import com.cq.httpapi.demo.exception.CQException.TooManyOptionsException;
import com.cq.httpapi.demo.handler.httphandler.message.GrpMsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.MsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.PriMsgHttpReqHandler;
import com.cq.httpapi.demo.kit.CQKit.CQSenderKit;
import com.cq.httpapi.demo.kit.ObjectKit;
import com.cq.httpapi.demo.kit.OptionKit;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.kit.TranslateKit;
import com.cq.httpapi.demo.service.CQService.PurchaseService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

@Component
public class PurchaseHandler {

    private static final String monthFlag = "-m";
    private static final String privateFlag = "-p";
    //    private static PurchaseService purchaseService = null;
    @Resource
    private PurchaseService purchaseService;

    public boolean checkService(String userId, String service) {
        if (purchaseService.haveService(userId, service)) {
            return true;
        } else {
            return false;
        }
    }

    // 新增服务
    // 若数据库中已存在服务，则延长有效期
    // 添加服务 {userId} {service} [-p] [-mX]
    @CQResponse
    public MessageResponse addPurchase(MsgHttpReqHandler msgHttpReqHandler) {
        String message = msgHttpReqHandler.getMessage();
        MessageResponse response = ObjectKit.getCQMessageResponse(msgHttpReqHandler);

        String addFlag = "添加服务 ";
        if (message.startsWith(addFlag) && CQSenderKit.CheckMsgSenderId(msgHttpReqHandler, User.DOLLYBELU.getUserId())) {

            StringBuilder msgBuilder = new StringBuilder(message.trim());
            String endTimeOption = "";
            int endTime = 0;

            try {
                // 提取 userId
                msgBuilder.delete(0, addFlag.length());
                String userId = msgBuilder.substring(0, msgBuilder.indexOf(" "));
                msgBuilder.delete(0, userId.length() + 1);
                if (msgBuilder.indexOf(privateFlag) > -1) {
                    userId = "p" + userId;
                    msgBuilder.delete(msgBuilder.indexOf(privateFlag), msgBuilder.indexOf(privateFlag) + 1);
                }

                // 提取 service
                msgBuilder.append(" ");
                String service = msgBuilder.substring(0, msgBuilder.indexOf(" "));
                msgBuilder.delete(0, service.length() + 1);

                // 截取 -mX 的 x
                endTimeOption = OptionKit.selectOption(message, monthFlag, monthFlag);
                if (endTimeOption.startsWith(monthFlag) && message.contains(monthFlag)) { // -mX 是X个月有效期
                    msgBuilder.append(" ");
                    endTime = Integer.parseInt(msgBuilder.substring(msgBuilder.indexOf(monthFlag) + monthFlag.length(),
                            msgBuilder.indexOf(" ", msgBuilder.indexOf(monthFlag))));
                } else if (!message.contains(monthFlag)) {  // 不含 -m 是一个月
                    endTime = 1;
                }

                if (!checkService(userId, service)) {
                    // 新建记录
                    String createUserId = CQSenderKit.GetMsgSenderId(msgHttpReqHandler); // 502063298
                    String strEndTime = TimeKit.parseTime(TimeKit.getDate(endTime * 30));

                    purchaseService.createPurchase(userId, service, strEndTime, createUserId);

                    response.setReply("成功为 " + userId + "\n添加服务 " + service + "\n有效期至 " + strEndTime);
                    response.setFlag(true);
                    return response;
                } else {
                    String modifiedUserId = CQSenderKit.GetMsgSenderId(msgHttpReqHandler); // 502063298
                    purchaseService.appendEndTimeByUserIdAndService(userId, service, new Long(endTime * 30), modifiedUserId);

                    response.setReply("成功延长服务有效期 " + endTime * 30 + " 天");
                    response.setFlag(true);
                }


            } catch (TooManyOptionsException e) {
                response.setReply("添加服务选项过多");
                response.setFlag(true);
            } catch (Exception e) {
                e.printStackTrace();
                return response;
            }
        }
        return response;
    }

    // 管理员根据记录id删除服务
    // 删除服务 {userId} {service}
    @CQResponse
    public MessageResponse deletePurchase(MsgHttpReqHandler msgHttpReqHandler) {

        String message = msgHttpReqHandler.getMessage();
        MessageResponse response = ObjectKit.getCQMessageResponse(msgHttpReqHandler);

        String deleteFlag = "删除服务 ";
        if (message.startsWith(deleteFlag) && CQSenderKit.CheckMsgSenderId(msgHttpReqHandler, User.DOLLYBELU.getUserId())) {
            StringBuilder stringBuilder = new StringBuilder(message);
            stringBuilder.delete(0, deleteFlag.length());

            String userId = stringBuilder.substring(0, stringBuilder.indexOf(" "));
            stringBuilder.delete(0, userId.length());
            if (message.contains(privateFlag)) {
                userId = "p" + userId;
                stringBuilder.delete(stringBuilder.indexOf(privateFlag), stringBuilder.indexOf(privateFlag) + privateFlag.length());
            }

            String service = stringBuilder.toString().trim();

            purchaseService.deletePurchaseByUserIdAndService(userId, service);

            response.setReply("成功删除服务");
            response.setFlag(true);

            return response;
        }
        return response;
    }

    // 超级管理员根据qq号或者群号获取服务
    // 查看服务 {userId} [-p]
    @CQResponse
    public MessageResponse SAGetPurchaseByGuild(MsgHttpReqHandler msgHttpReqHandler) {
        String message = msgHttpReqHandler.getMessage();
        MessageResponse response = ObjectKit.getCQMessageResponse(msgHttpReqHandler);

        String getFlag = "查看服务 ";
        if (message.startsWith(getFlag) && CQSenderKit.CheckMsgSenderId(msgHttpReqHandler, User.DOLLYBELU.getUserId())) {

            StringBuilder stringBuilder = new StringBuilder(message);
            stringBuilder.delete(0, getFlag.length());
            stringBuilder.append(" ");
            String userId = stringBuilder.substring(0, stringBuilder.indexOf(" "));
            if (message.contains(privateFlag)) {
                userId = "p" + userId;
            }
            ArrayList<Purchase> purchases = purchaseService.getPurchaseByUserId(userId);
            StringBuilder sbResponse = new StringBuilder();
            if (purchases.size() > 0) {
                sbResponse.append(userId + " 的服务\n");
                for (Purchase purchase : purchases) {
                    sbResponse.append(TranslateKit.TranslateProp("purchase", "service", purchase.getService()));
                    sbResponse.append("  有效期至 ");
                    sbResponse.append(purchase.getEndTime());
                    sbResponse.append("\n");
                }
                response.setReply(sbResponse.toString().trim());
                response.setFlag(true);
                return response;
            }
        }
        return response;
    }

    // 查看自己的服务
    // 查看服务
    @CQResponse
    public MessageResponse getOwnPurchase(MsgHttpReqHandler msgHttpReqHandler) {
        String message = msgHttpReqHandler.getMessage();
        MessageResponse response = null;

        if (GrpMsgHttpReqHandler.class.isInstance(msgHttpReqHandler)) {

            GrpMsgHttpReqHandler grpMsgHttpReqHandler = (GrpMsgHttpReqHandler) msgHttpReqHandler;

            String getFlag = "查看服务";
            if (message.equals(getFlag) && CQSenderKit.isAdminOrOwner(grpMsgHttpReqHandler)) {

                response = new GroupMessageResponse();
                response.setFlag(false);

                String userId = grpMsgHttpReqHandler.getGroupId();

                ArrayList<Purchase> purchases = purchaseService.getPurchaseByUserId(userId);
                StringBuilder sbResponse = new StringBuilder();
                if (purchases.size() > 0) {
                    for (Purchase purchase : purchases) {
                        sbResponse.append(TranslateKit.TranslateProp("purchase", "service", purchase.getService()));
                        sbResponse.append("  有效期至 ");
                        sbResponse.append(purchase.getEndTime());
                        sbResponse.append("\n");
                    }
                    response.setReply(sbResponse.toString().trim());
                    response.setFlag(true);
                    return response;
                }
            }
        } else if (PriMsgHttpReqHandler.class.isInstance(msgHttpReqHandler)) {

            response = new PrivateMessageResponse();
            response.setFlag(false);

            String getFlag = "查看服务";
            if (message.equals(getFlag)) {
                String userId = CQSenderKit.GetMsgSenderId(msgHttpReqHandler);
                userId = "p" + userId;

                ArrayList<Purchase> purchases = purchaseService.getPurchaseByUserId(userId);
                StringBuilder sbResponse = new StringBuilder();
                if (purchases.size() > 0) {
                    for (Purchase purchase : purchases) {
                        sbResponse.append(TranslateKit.TranslateProp("purchase", "service", purchase.getService()));
                        sbResponse.append("  有效期至 ");
                        sbResponse.append(purchase.getEndTime());
                        sbResponse.append("\n");
                    }
                    response.setReply(sbResponse.toString().trim());
                    response.setFlag(true);
                    return response;
                }
            }
        }
        return response;
    }
}
