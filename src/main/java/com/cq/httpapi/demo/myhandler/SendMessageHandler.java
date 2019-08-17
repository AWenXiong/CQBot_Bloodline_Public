package com.cq.httpapi.demo.myhandler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.config.Account;
import com.cq.httpapi.demo.dto.User;
import com.cq.httpapi.demo.dto.response.message.PrivateMessageResponse;
import com.cq.httpapi.demo.dto.send.GetGroupList;
import com.cq.httpapi.demo.dto.send.SendGroupMessage;
import com.cq.httpapi.demo.dto.send.SendPrivateMessage;
import com.cq.httpapi.demo.handler.httphandler.message.MsgHttpReqHandler;
import com.cq.httpapi.demo.kit.CQKit.CQSenderKit;

import java.util.HashSet;

public class SendMessageHandler {

    private static final String sendGroupMessageFlag = "发送群消息 ";
    private static final String sendPrivateMessageFlag = "发送私聊消息 ";

    // 向所有群发送消息
    // 发送群消息 [groupId] {message} [-a]
    public static PrivateMessageResponse sendGroupMessage(MsgHttpReqHandler msgHttpReqHandler) {
        PrivateMessageResponse privateMessageResponse = new PrivateMessageResponse();
        privateMessageResponse.setFlag(false);
        SendGroupMessage sendGroupMessage = new SendGroupMessage();
        String message = msgHttpReqHandler.getMessage();

        String allFlag = "-a";
        if (message.startsWith(sendGroupMessageFlag) /*&&
                CQSenderKit.CheckMsgSenderId(msgHttpReqHandler, User.DOLLYBELU.getUserId())*/) {
            StringBuilder stringBuilder = new StringBuilder(message);
            stringBuilder.delete(0, sendGroupMessageFlag.length());

            if (message.contains(allFlag)) {
                stringBuilder.delete(stringBuilder.indexOf(allFlag), stringBuilder.indexOf(allFlag) + allFlag.length());
                String sendMessage = stringBuilder.toString().trim();
                sendGroupMessage.setMessage(sendMessage);
                for (Account account : Account.values()) {
                    if (account.getId().equals("502063298") || account.getId().equals("2078003617")) {
                        continue;
                    }
                    HashSet<String> groups = getGroupList(account.getId());
                    for (String group : groups) {
                        sendGroupMessage.setGroup_id(group);
                        sendGroupMessage.setIp(account.getIp());
                        sendGroupMessage.execute();
                    }
                }
                privateMessageResponse.setReply("已对所有群发送消息");
                privateMessageResponse.setFlag(true);
                return privateMessageResponse;
            } else {
                String groupId = stringBuilder.substring(0, stringBuilder.indexOf(" ")).trim();
                stringBuilder.delete(0, groupId.length());
                String sendMessage = stringBuilder.toString().trim();
                sendGroupMessage.setIp(Account.getIpById(msgHttpReqHandler.getSelfId()));
                sendGroupMessage.setGroup_id(groupId);
                sendGroupMessage.setMessage(sendMessage);
                sendGroupMessage.execute();
                privateMessageResponse.setReply("已对群 " + groupId + "\n发送消息 " + sendMessage);
                privateMessageResponse.setFlag(true);
                return privateMessageResponse;
            }
        }
        return privateMessageResponse;
    }

    // 向某人发送私聊消息
    // 发送私聊消息 {userId} {message}
    public static PrivateMessageResponse sendPrivateMessage(MsgHttpReqHandler msgHttpReqHandler) {
        PrivateMessageResponse privateMessageResponse = new PrivateMessageResponse();
        privateMessageResponse.setFlag(false);
        SendPrivateMessage sendPrivateMessage = new SendPrivateMessage();
        String message = msgHttpReqHandler.getMessage();

        if (message.startsWith(sendPrivateMessageFlag) &&
                CQSenderKit.CheckMsgSenderId(msgHttpReqHandler, User.DOLLYBELU.getUserId())) {
            StringBuilder stringBuilder = new StringBuilder(message);
            stringBuilder.delete(0, sendPrivateMessageFlag.length());
            String userId = stringBuilder.substring(0, stringBuilder.indexOf(" ")).trim();
            stringBuilder.delete(0, userId.length());
            String sendMessage = stringBuilder.toString().trim();
            sendPrivateMessage.setIp(Account.getIpById(msgHttpReqHandler.getSelfId()));
            sendPrivateMessage.setUser_id(userId);
            sendPrivateMessage.setMessage(sendMessage);
            sendPrivateMessage.execute();
            privateMessageResponse.setReply("已对 " + userId + "\n发送消息 " + sendMessage);
            privateMessageResponse.setFlag(true);
            return privateMessageResponse;
        }

        return privateMessageResponse;
    }

    // 由于响应数据为一个json数组，所以多写一个方法进行提取
    private static HashSet<String> getGroupList(String selfId) {
        HashSet res = new HashSet();
        JSONObject jsonGroupList = GetGroupList.getGroupList(Account.getById(selfId).getIp());
        JSONArray groupList = jsonGroupList.getJSONArray("data");
        if (groupList != null) {
            for (int i = 0; i < groupList.size(); i++) {
                JSONObject group = groupList.getJSONObject(i);
                res.add(group.getString("group_id"));
            }
        }
        return res;
    }
}
