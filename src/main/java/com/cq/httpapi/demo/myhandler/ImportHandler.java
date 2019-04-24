package com.cq.httpapi.demo.myhandler;

import com.alibaba.fastjson.JSONArray;
import com.cq.httpapi.demo.dto.User;
import com.cq.httpapi.demo.dto.response.message.GroupMessageResponse;
import com.cq.httpapi.demo.handler.httphandler.message.GrpMsgHttpReqHandler;
import com.cq.httpapi.demo.kit.SQLKit;
import com.cq.httpapi.demo.kit.SenderKit;
import com.cq.httpapi.demo.service.CardService;

public class ImportHandler {

    public static GroupMessageResponse importHandler(GrpMsgHttpReqHandler grpMsgHttpReqHandler, CardService cardService) {
        String msg = grpMsgHttpReqHandler.getMessage();
        GroupMessageResponse groupMessageResponse = new GroupMessageResponse();
        groupMessageResponse.setFlag(false);
        if (msg.equals("import") && SenderKit.CheckGrpMsgSenderId(grpMsgHttpReqHandler, User.DOLLYBELU.getUserId())) {
//            JSONArray jsonArray = SQLKit.getCardData("C:\\Users\\Administrator\\Desktop\\2019-3-27Data");
            JSONArray jsonArray = SQLKit.getCardData("C:\\Users\\Public\\Downloads\\bloodline\\CardData");
            SQLKit.importDataToDB(cardService, jsonArray);
        }
        return groupMessageResponse;
    }
}
