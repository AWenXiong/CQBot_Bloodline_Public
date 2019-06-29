package com.cq.httpapi.demo.myhandler;

import com.cq.httpapi.demo.dto.response.message.GroupMessageResponse;
import com.cq.httpapi.demo.dto.response.message.MessageResponse;
import com.cq.httpapi.demo.dto.response.message.PrivateMessageResponse;
import com.cq.httpapi.demo.entity.CQ.BloodlineEquipment;
import com.cq.httpapi.demo.entity.CQ.BloodlineItem;
import com.cq.httpapi.demo.entity.CQ.Card;
import com.cq.httpapi.demo.handler.httphandler.message.GrpMsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.MsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.PriMsgHttpReqHandler;
import com.cq.httpapi.demo.kit.ObjectKit;
import com.cq.httpapi.demo.service.CQService.BloodlineEquipmentService;
import com.cq.httpapi.demo.service.CQService.BloodlineItemService;
import com.cq.httpapi.demo.service.CQService.CardService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ItemHandler {

    @Resource
    private BloodlineItemService itemService;
    @Resource
    private BloodlineEquipmentService equipmentService;
    @Resource
    private CardService cardService;

    public MessageResponse itemChecker(MsgHttpReqHandler msgHttpReqHandler) {

        String message = msgHttpReqHandler.getMessage();
        MessageResponse response = ObjectKit.getCQMessageResponse(msgHttpReqHandler);

        if (message.endsWith("主手") || message.endsWith("副手")) {
            try {
                String cardName = message.substring(0, message.length() - 2);
                String type = message.substring(message.length() - 2);
                Card card = cardService.getAllInfo(cardName);
                if (card != null) {
                    Long cardId = card.getId();
                    BloodlineEquipment equipmentRecord = equipmentService.getEquipment(cardId, type);
                    BloodlineItem equipment = itemService.getItemById(equipmentRecord.getEquipmentId());
                    String description = equipment.getDescription();
                    String reply = card.getFullname() + type + "：" + equipment.getName() + "\n  " + description;
                    response.setReply(reply);
                    response.setFlag(true);
                } else {
                    response.setFlag(false);
                }
                return response;
            } catch (Exception e) {
                response.setFlag(false);
            }
        }
        if (message.endsWith("装备")) {
            try {
                String cardName = message.substring(0, message.length() - 2);
                Card card = cardService.getAllInfo(cardName);
                if (card != null) {
                    Long cardId = card.getId();
                    BloodlineEquipment equipmentRecord1 = equipmentService.getEquipment(cardId, "主手");
                    BloodlineEquipment equipmentRecord2 = equipmentService.getEquipment(cardId, "副手");
                    BloodlineItem equipment1 = itemService.getItemById(equipmentRecord1.getEquipmentId());
                    BloodlineItem equipment2 = itemService.getItemById(equipmentRecord2.getEquipmentId());
                    String reply = card.getFullname() + "装备：\n" +
                            "· 主手：" + equipment1.getName() + "\n  " + equipment1.getDescription() + "\n" +
                            "· 副手：" + equipment2.getName() + "\n  " + equipment2.getDescription();
                    response.setReply(reply);
                    response.setFlag(true);
                } else {
                    response.setFlag(false);
                }
                return response;
            } catch (Exception e) {
                response.setFlag(false);
            }
        }

        return response;
    }

    public MessageResponse itemManager(MsgHttpReqHandler msgHttpReqHandler) {
        String message = msgHttpReqHandler.getMessage();
        MessageResponse response = ObjectKit.getCQMessageResponse(msgHttpReqHandler);

        try {
            if (message.startsWith("修改装备 ")) { // 修改装备 夜刃 主手 轰击晨星
                StringBuilder messageBuilder = new StringBuilder(message);
                messageBuilder.delete(0, messageBuilder.indexOf(" ") + 1);

                String cardName = messageBuilder.substring(0, messageBuilder.indexOf(" "));
                messageBuilder.delete(0, messageBuilder.indexOf(" ") + 1);

                String type = messageBuilder.substring(0, messageBuilder.indexOf(" "));
                messageBuilder.delete(0, messageBuilder.indexOf(" ") + 1);

                String equipmentName = messageBuilder.toString().trim();

                if (equipmentService.createEquipmentRecord(cardName, equipmentName, type)) {
                    response.setFlag(true);
                    response.setReply("修改 " + cardName + " " + type + " 成功");
                    return response;
                } else {
                    response.setFlag(true);
                    response.setReply("修改装备失败");
                    return response;
                }
            }

            if (message.startsWith("新增物品 ")) { //新增物品 武器 冰河壁垒 1234
                StringBuilder stringBuilder = new StringBuilder(message);
                stringBuilder.delete(0, stringBuilder.indexOf(" ") + 1);

                String itemType = stringBuilder.substring(0, stringBuilder.indexOf(" "));
                stringBuilder.delete(0, stringBuilder.indexOf(" ") + 1);

                String itemName = stringBuilder.substring(0, stringBuilder.indexOf(" "));
                stringBuilder.delete(0, stringBuilder.indexOf(" ") + 1);

                String description = stringBuilder.toString().trim();

                if (itemService.createItem(itemName, itemType, description)) {
                    response.setFlag(true);
                    response.setReply("新增物品成功");
                } else {
                    response.setFlag(true);
                    response.setReply("新增物品失败");
                }
                return response;
            }

            // 修改物品属性 冰河壁垒 武器
            // 修改物品描述 冰河壁垒 1234
            if (message.startsWith("修改物品")) {
                if (message.startsWith("修改物品属性 ")) {
                    StringBuilder stringBuilder = new StringBuilder(message);
                    stringBuilder.delete(0, stringBuilder.indexOf(" ") + 1);

                    String itemName = stringBuilder.substring(0, stringBuilder.indexOf(" "));
                    stringBuilder.delete(0, stringBuilder.indexOf(" ") + 1);

                    String type = stringBuilder.toString().trim();

                    if (itemService.updateItemType(itemName, type)) {
                        response.setFlag(true);
                        response.setReply("修改物品属性成功");
                    } else {
                        response.setFlag(true);
                        response.setReply("修改物品属性失败");
                    }
                    return response;
                }
                if (message.startsWith("修改物品描述 ")) {
                    StringBuilder stringBuilder = new StringBuilder(message);
                    stringBuilder.delete(0, stringBuilder.indexOf(" ") + 1);

                    String itemName = stringBuilder.substring(0, stringBuilder.indexOf(" "));
                    stringBuilder.delete(0, stringBuilder.indexOf(" ") + 1);

                    String desc = stringBuilder.toString().trim();

                    if (itemService.updateItemDesc(itemName, desc)) {
                        response.setFlag(true);
                        response.setReply("修改物品描述成功");
                    } else {
                        response.setFlag(true);
                        response.setReply("修改物品描述失败");
                    }
                    return response;
                }
            }

            if (message.startsWith("删除物品 ")) { // 删除物品 冰河壁垒
                StringBuilder stringBuilder = new StringBuilder(message);
                stringBuilder.delete(0, stringBuilder.indexOf(" ") + 1);

                String itemName = stringBuilder.toString().trim();
                if (itemService.deleteItem(itemName)) {
                    response.setFlag(true);
                    response.setReply("删除物品成功");
                } else {
                    response.setFlag(true);
                    response.setReply("删除物品失败");
                }
                return response;
            }
        } catch (Exception e) {
            response.setFlag(false);
        }
        return response;
    }
}
