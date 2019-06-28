package com.cq.httpapi.demo.myhandler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.User;
import com.cq.httpapi.demo.dto.response.message.PrivateMessageResponse;
import com.cq.httpapi.demo.dto.send.GetGroupList;
import com.cq.httpapi.demo.dto.send.SendGroupMessage;
import com.cq.httpapi.demo.dto.send.SendPrivateMessage;
import com.cq.httpapi.demo.handler.httphandler.message.MsgHttpReqHandler;
import com.cq.httpapi.demo.kit.CQKit.CQSenderKit;

import java.util.HashSet;

public class SendMessageHandler {

    private static final String testFlag = "更新";
    private static final String testFinishedFlag = "完成更新";
    private static final String sendTestMessage = "现在开始更新，从现在开始只保证 \"好感度正向查询\"和\"好感度逆向查询\" 两个功能的正常使用";
    private static final String sendTestFinishedMessage = "已完成更新，所有功能恢复正常";
    private static final String sendGroupMessageFlag = "发送群消息 ";
    private static final String sendPrivateMessageFlag = "发送私聊消息 ";

    // 向所有群发送进行更新的提示
    // 更新
    public static PrivateMessageResponse sendTestMessage(MsgHttpReqHandler msgHttpReqHandler) {
        PrivateMessageResponse privateMessageResponse = new PrivateMessageResponse();
        privateMessageResponse.setFlag(false);
        SendGroupMessage sendGroupMessage = new SendGroupMessage();

        String msg = msgHttpReqHandler.getMessage();
        if (CQSenderKit.CheckMsgSenderId(msgHttpReqHandler, User.DOLLYBELU.getUserId()) &&
                (msg.equals(testFlag)) || msg.equals(testFinishedFlag)) {
            HashSet<String> groupIds = getGroupList();
            for (String groupId : groupIds) {
                sendGroupMessage.setGroup_id(groupId);
                if (msg.equals(testFlag)) {
                    sendGroupMessage.setMessage(sendTestMessage);
                } else if (msg.equals(testFinishedFlag)) {
                    sendGroupMessage.setMessage(sendTestFinishedMessage);
                }
                sendGroupMessage.execute();
            }
            privateMessageResponse.setReply("已对所有群发送更新提示");
            privateMessageResponse.setFlag(true);
            return privateMessageResponse;
        }
        return privateMessageResponse;
    }

    // 向所有群发送消息
    // 发送群消息 [groupId] {message} [-a]
    public static PrivateMessageResponse sendGroupMessage(MsgHttpReqHandler msgHttpReqHandlerl) {
        PrivateMessageResponse privateMessageResponse = new PrivateMessageResponse();
        privateMessageResponse.setFlag(false);
        SendGroupMessage sendGroupMessage = new SendGroupMessage();
        String message = msgHttpReqHandlerl.getMessage();

        String allFlag = "-a";
        if (message.startsWith(sendGroupMessageFlag) &&
                CQSenderKit.CheckMsgSenderId(msgHttpReqHandlerl, User.DOLLYBELU.getUserId())) {
            StringBuilder stringBuilder = new StringBuilder(message);
            stringBuilder.delete(0, sendGroupMessageFlag.length());

            if (message.contains(allFlag)) {
                stringBuilder.delete(stringBuilder.indexOf(allFlag), stringBuilder.indexOf(allFlag) + allFlag.length());
                String sendMessage = stringBuilder.toString().trim();
                sendGroupMessage.setMessage(sendMessage);
                HashSet<String> groups = getGroupList();
                for (String group : groups) {
                    sendGroupMessage.setGroup_id(group);
                    sendGroupMessage.execute();
                }
                privateMessageResponse.setReply("已对所有群发送消息");
                privateMessageResponse.setFlag(true);
                return privateMessageResponse;
            } else {
                String groupId = stringBuilder.substring(0, stringBuilder.indexOf(" ")).trim();
                stringBuilder.delete(0, groupId.length());
                String sendMessage = stringBuilder.toString().trim();
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
    private static HashSet<String> getGroupList() {
        HashSet res = new HashSet();
        JSONObject jsonGroupList = GetGroupList.getGroupList();
        JSONArray groupList = jsonGroupList.getJSONArray("data");
        for (int i = 0; i < groupList.size(); i++) {
            JSONObject group = groupList.getJSONObject(i);
            res.add(group.getString("group_id"));
        }
        return res;
    }

}
