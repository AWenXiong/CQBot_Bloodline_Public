package com.cq.httpapi.demo.myhandler;

import com.cq.httpapi.demo.annotation.cqannotation.CQResponse;
import com.cq.httpapi.demo.dto.response.message.GroupMessageResponse;
import com.cq.httpapi.demo.dto.response.message.MessageResponse;
import com.cq.httpapi.demo.dto.response.message.PrivateMessageResponse;
import com.cq.httpapi.demo.entity.Card;
import com.cq.httpapi.demo.handler.httphandler.message.GrpMsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.MsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.PriMsgHttpReqHandler;
import com.cq.httpapi.demo.kit.SenderKit;
import com.cq.httpapi.demo.service.CQService.CardService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

@Component
public class CardHandler {

    @Resource
    private CardService cardService;

    private static String showProp(Object o) {
        if (o != null) {
            return o.toString();
        } else {
            return "无";
        }
    }

    // 对卡牌表进行查
    @CQResponse
    public MessageResponse cardChecker(MsgHttpReqHandler msgHttpReqHandler) {
        String message = msgHttpReqHandler.getMessage();
        MessageResponse response = null;

        if (GrpMsgHttpReqHandler.class.isInstance(msgHttpReqHandler)) {
            response = new GroupMessageResponse();
            response.setFlag(false);
        } else if (PriMsgHttpReqHandler.class.isInstance(msgHttpReqHandler)) {
            response = new PrivateMessageResponse();
            response.setFlag(false);
        }

        // 查询卡的好感信息
        String hobbyKeyWord = "喜欢什么";
        if (message.endsWith(hobbyKeyWord)) {
            int endIndex = message.indexOf(hobbyKeyWord);
            String nickname = message.substring(0, endIndex);
            if (cardService.existCard(nickname)) {
                Card card = cardService.getHobby(nickname);
                StringBuilder reply = new StringBuilder();
                reply.append(card.getFullname() + "\n");
                reply.append("喜欢：");
                reply.append(showProp(card.getFavourate()) + "\n");
                reply.append("不喜欢：");
                reply.append(showProp(card.getDislike()));
                response.setReply(reply.toString().trim());
                response.setFlag(true);
                return response;
            }
        }

        // 逆向查询卡的好感信息
        String reverseFavourateKeyWord = "谁喜欢";
        if (message.startsWith(reverseFavourateKeyWord)) {
            int startIndex = message.indexOf(reverseFavourateKeyWord) + reverseFavourateKeyWord.length();
            String item = message.substring(startIndex);
            ArrayList<Card> list = cardService.getCardByFavouriteItem(item);
            if (list.size() != 0) {
                StringBuffer stringBuffer = new StringBuffer("喜欢 " + item + " 的有：\n    ");
                for (Card card : list) {
                    stringBuffer.append(card.getFullname() + ", ");
                }
                response.setReply(stringBuffer.substring(0, stringBuffer.length() - 2));
                response.setFlag(true);
                return response;
            }
        }

        // 逆向查询卡的好感信息
        String reverseDislikeKeyWord = "谁不喜欢";
        if (message.startsWith(reverseDislikeKeyWord)) {
            int startIndex = message.indexOf(reverseDislikeKeyWord) + reverseDislikeKeyWord.length();
            String item = message.substring(startIndex);
            ArrayList<Card> list = cardService.getCardByDislikeItem(item);
            if (list.size() != 0) {
                StringBuffer stringBuffer = new StringBuffer("不喜欢 " + item + " 的有：\n    ");
                for (Card card : list) {
                    stringBuffer.append(card.getFullname() + ", ");
                }
                response.setReply(stringBuffer.substring(0, stringBuffer.length() - 2));
                response.setFlag(true);
                return response;
            }
        }

        // 查询卡的技能信息
        String skillCheckFlag = "技能";
        if (message.endsWith(skillCheckFlag)) {
            int endIndex = message.indexOf(skillCheckFlag);
            String nickname = message.substring(0, endIndex).trim();
            Card card = cardService.getSkill(nickname);
            StringBuilder reply = new StringBuilder();
            reply.append(card.getFullname());
            reply.append(" 技能\n");
            reply.append("----------------\n必杀：" + showProp(card.getSkill1()) + "\n");
            reply.append("----------------\n天赋：" + showProp(card.getSkill2()) + "\n");
            reply.append("----------------\n队长：" + showProp(card.getSkill3()) + "\n");
            reply.append("----------------\n命运：" + showProp(card.getSkill4()) + "\n");
            response.setReply(reply.toString().trim());
            response.setFlag(true);
            return response;
        }

        // 查询卡的命运伙伴信息
        String partnerCheckFlag = "命运";
        if (message.endsWith(partnerCheckFlag)) {
            int endIndex = message.indexOf(partnerCheckFlag);
            String nickname = message.substring(0, endIndex);
            Card card = cardService.getPartner(nickname);
            if (card.getPartner() != null && !card.getPartner().isEmpty()) {
                StringBuilder reply = new StringBuilder();
                reply.append(card.getFullname() + " 命运伙伴\n");
                reply.append(showProp(card.getPartner()));
                while (reply.indexOf("|") != -1) {
                    reply.replace(reply.indexOf("|"), reply.indexOf("|") + 1, ", ");
                }
                response.setReply(reply.toString());
                response.setFlag(true);
                return response;
            }
        }

        // 查询卡的阵营信息
        String factionCheckFlag = "阵营";
        if (message.endsWith(factionCheckFlag)) {
            int endIndex = message.indexOf(factionCheckFlag);
            String nickname = message.substring(0, endIndex);
            Card card = cardService.getFaction(nickname);
            if (card != null) {
                response.setReply(card.getFullname() + " 阵营为 " + showProp(card.getFaction()));
                response.setFlag(true);
                return response;
            }
        }

        // 逆向查询卡的阵营信息
        String factionReverseCheckFlag = "阵营有谁";
        if (message.endsWith(factionReverseCheckFlag)) {
            int endIndex = message.indexOf(factionReverseCheckFlag);
            String faction = message.substring(0, endIndex);
            ArrayList<Card> cards = cardService.getCardByFaction(faction);
            StringBuilder reply = new StringBuilder();
            reply.append(faction + " 阵营的有:\n");
            for (Card card : cards) {
                reply.append(card.getFullname() + ", ");
            }
            response.setReply(reply.toString());
            response.setFlag(true);
            return response;
        }

        // 查询卡的全部信息
        String allInfoCheckFlag = "的所有信息";
        if (message.endsWith(allInfoCheckFlag)) {
            int endIndex = message.indexOf(allInfoCheckFlag);
            String nickname = message.substring(0, endIndex).trim();
            Card card = cardService.getAllInfo(nickname);
            if (card != null) {
                StringBuilder reply = new StringBuilder();
                reply.append(card.getFullname() + "\n");
                String career = showProp(card.getCareer());
                switch (career) {
                    case "1": {
                        career = "战士";
                        break;
                    }
                    case "2": {
                        career = "刺客";
                        break;
                    }
                    case "3": {
                        career = "射手";
                        break;
                    }
                    case "4": {
                        career = "法师";
                        break;
                    }
                    case "5": {
                        career = "牧师";
                        break;
                    }
                }
                reply.append(career + " ");
                reply.append(showProp(card.getColor()) + " ");
                reply.append(showProp(card.getFaction()) + "\n");
                reply.append("----------------\n必杀技：" + showProp(card.getSkill1()) + "\n");
                reply.append("----------------\n天赋技：" + showProp(card.getSkill2()) + "\n");
                reply.append("----------------\n队长技：" + showProp(card.getSkill3()) + "\n");
                reply.append("----------------\n命运技：" + showProp(card.getSkill4()) + "\n");
                reply.append("----------------\n命运链接：" + showProp(card.getPartner()) + "\n");
                reply.append("喜欢的物品：" + showProp(card.getFavourate()) + "\n");
                reply.append("不喜欢的物品：" + showProp(card.getDislike()) + "\n");
                response.setReply(reply.toString().trim());
                response.setFlag(true);
                return response;
            }
        }

        return response;
    }

    // 对卡牌表进行增删改
    // 添加卡片
    // 追加喜欢的物品、追加不喜欢的物品
    // 追加别名
    // 删除卡片
    // 修改技能
    // 修改阵营
    // 修改属性
    // 修改物种
    // 修改性别
    // 修改命运伙伴
    // 修改喜欢的物品
    // 修改不喜欢的物品
    // 修改别名
    // 修改全名
    @CQResponse
//    public static MessageResponse cardHandler(MsgHttpReqHandler msgHttpReqHandler) {
    public MessageResponse cardHandler(MsgHttpReqHandler msgHttpReqHandler) {
        String message = msgHttpReqHandler.getMessage();
        MessageResponse response = null;

        if (GrpMsgHttpReqHandler.class.isInstance(msgHttpReqHandler)) {
            response = new GroupMessageResponse();
            response.setFlag(false);
        } else if (PriMsgHttpReqHandler.class.isInstance(msgHttpReqHandler)) {
            response = new PrivateMessageResponse();
            response.setFlag(false);
        }

        if (SenderKit.CheckMsgSenderId(msgHttpReqHandler, "502063298")) {
            //添加卡片
            String addCardMessage = "添加卡片 ";
            if (message.startsWith(addCardMessage)) {
                int fullnameStartIndex = message.indexOf(addCardMessage) + addCardMessage.length();
                String fullname = message.substring(fullnameStartIndex);

                if (cardService.createNewCard(fullname, SenderKit.GetMsgSenderId(msgHttpReqHandler))) {
                    response.setReply("成功添加卡片，卡片名为：" + fullname);
                    response.setFlag(true);
                    return response;
                }
            }

            // 追加喜欢的物品
            String appendFavourateMessage = "追加喜欢的物品 ";
            if (message.startsWith(appendFavourateMessage)) {
                StringBuilder sbMessage = new StringBuilder(message);
                sbMessage.delete(0, appendFavourateMessage.length());
                String nickname = sbMessage.substring(0, sbMessage.indexOf(" "));
                String appendFavourate = sbMessage.substring(sbMessage.indexOf(" ") + 1).trim();
                if (cardService.appendFavourite(nickname, " " + appendFavourate, "多莉贝露")) {
                    response.setReply("为 " + nickname + " 追加喜欢的物品 " + appendFavourate + " 成功");
                    response.setFlag(true);
                    return response;
                }
            }

            // 追加不喜欢的物品
            String appendDislikeMessage = "追加不喜欢的物品 ";
            if (message.startsWith(appendDislikeMessage)) {
                StringBuilder sbMessage = new StringBuilder(message);
                sbMessage.delete(0, appendDislikeMessage.length());
                String nickname = sbMessage.substring(0, sbMessage.indexOf(" "));
                String appendDislike = sbMessage.substring(sbMessage.indexOf(" ") + 1).trim();
                if (cardService.appendDislike(nickname, " " + appendDislike, "多莉贝露")) {
                    response.setReply("为 " + nickname + " 追加不喜欢的物品 " + appendDislike + " 成功");
                    response.setFlag(true);
                    return response;
                }
            }

            // 追加别名
            String appendNicknameMessage = "追加别名 ";
            if (message.startsWith(appendNicknameMessage)) {
                StringBuilder sbMessage = new StringBuilder(message);
                sbMessage.delete(0, appendNicknameMessage.length());
                String nickname = sbMessage.substring(0, sbMessage.indexOf(" "));
                String appendNickname = sbMessage.substring(sbMessage.indexOf(" ") + 1).trim();
                if (cardService.appendNickName(nickname, appendNickname, "多莉贝露")) {
                    response.setReply("为 " + nickname + " 追加别名 " + appendNickname + " 成功");
                    response.setFlag(true);
                    return response;
                }
            }

            // 删除卡片
            String deleteMessage = "删除卡片 ";
            if (message.startsWith(deleteMessage)) {
                int deleteCardStartIndex = message.indexOf(deleteMessage) + deleteMessage.length();
                String nickname = message.substring(deleteCardStartIndex);

                if (cardService.deleteCard(nickname, SenderKit.GetMsgSenderId(msgHttpReqHandler))) {
                    response.setReply("成功删除卡片 " + nickname);
                    response.setFlag(true);
                    return response;
                } else {
                    response.setReply("删除卡片 " + nickname + "失败");
                    response.setFlag(true);
                    return response;
                }
            }

            // 修改卡片技能
            String updateSkillFlag = "修改技能 ";
            String updateSkill1Flag = "必杀 ";
            String updateSkill2Flag = "天赋 ";
            String updateSkill3Flag = "队长 ";
            String updateSkill4Flag = "命运 ";
            if (message.startsWith(updateSkillFlag)) {
                StringBuilder sbMessage = new StringBuilder(message.trim());
                // 删除 修改技能[空格]
                sbMessage.delete(0, updateSkillFlag.length());
                String nickname = sbMessage.substring(0, sbMessage.indexOf(" "));

                StringBuilder reply = new StringBuilder();
                // 删除 昵称[空格]
                sbMessage.delete(0, sbMessage.indexOf(" ") + 1);
                sbMessage.append(" ");
                if (sbMessage.indexOf(updateSkill1Flag) > -1) {
                    int bsStartIndex = sbMessage.indexOf(updateSkill1Flag) + updateSkill1Flag.length();
                    String skill1 = sbMessage.substring(bsStartIndex, sbMessage.indexOf(" ", bsStartIndex)).trim();
                    if (cardService.updateCardSkill1(nickname, skill1)) {
                        reply.append("为 " + nickname + " 修改必杀技成功\n");
                    }
                }
                if (sbMessage.indexOf(updateSkill2Flag) > -1) {
                    int tfStartIndex = sbMessage.indexOf(updateSkill2Flag) + updateSkill2Flag.length();
                    String skill2 = sbMessage.substring(tfStartIndex, sbMessage.indexOf(" ", tfStartIndex)).trim();
                    if (cardService.updateCardSkill2(nickname, skill2)) {
                        reply.append("为 " + nickname + " 修改天赋技成功\n");
                    }
                }
                if (sbMessage.indexOf(updateSkill3Flag) > -1) {
                    int dzStartIndex = sbMessage.indexOf(updateSkill3Flag) + updateSkill3Flag.length();
                    String skill3 = sbMessage.substring(dzStartIndex, sbMessage.indexOf(" ", dzStartIndex)).trim();
                    if (cardService.updateCardSkill3(nickname, skill3)) {
                        reply.append("为 " + nickname + " 修改队长技成功\n");
                    }
                }
                if (sbMessage.indexOf(updateSkill4Flag) > -1) {
                    int myStartIndex = sbMessage.indexOf(updateSkill4Flag) + updateSkill4Flag.length();
                    String skill4 = sbMessage.substring(myStartIndex, sbMessage.indexOf(" ", myStartIndex)).trim();
                    if (cardService.updateCardSkill4(nickname, skill4)) {
                        reply.append("为 " + nickname + " 修改命运技成功\n");
                    }
                }
                if (!reply.toString().isEmpty()) {
                    response.setReply(reply.toString().trim());
                    response.setFlag(true);
                    return response;
                }
            }

            // 修改阵营
            String updateFactionFlag = "修改阵营 ";
            if (message.startsWith(updateFactionFlag)) {
                StringBuilder sbMessage = new StringBuilder(message);
                sbMessage.delete(0, updateFactionFlag.length());
                String nickname = sbMessage.substring(0, sbMessage.indexOf(" "));
                String faction = sbMessage.substring(sbMessage.indexOf(" ") + 1).trim();
                if (cardService.updateCardFaction(nickname, faction)) {
                    response.setReply("为 " + nickname + " 修改阵营成功");
                    response.setFlag(true);
                    return response;
                }
            }

            // 修改属性
            String updateColorFlag = "修改属性 ";
            if (message.startsWith(updateColorFlag)) {
                StringBuilder sbMessage = new StringBuilder(message);
                sbMessage.delete(0, updateColorFlag.length());
                String nickname = sbMessage.substring(0, sbMessage.indexOf(" "));
                String color = sbMessage.substring(sbMessage.indexOf(" ") + 1).trim();
                if (cardService.updateCardColor(nickname, color)) {
                    response.setReply("为 " + nickname + " 修改属性 " + color + " 成功");
                    response.setFlag(true);
                    return response;
                }
            }

            // 修改物种
            String updateSpeciesFlag = "修改物种 ";
            if (message.startsWith(updateSpeciesFlag)) {
                StringBuilder stringBuilder = new StringBuilder(message);
                stringBuilder.delete(0, updateSpeciesFlag.length());
                String nickname = stringBuilder.substring(0, stringBuilder.indexOf(" "));
                String species = stringBuilder.substring(stringBuilder.indexOf(" ") + 1).trim();
                if (cardService.updateCardSpecies(nickname, species)) {
                    response.setReply("为 " + nickname + " 修改物种 " + species + " 成功");
                    response.setFlag(true);
                    return response;
                }
            }

            // 修改职业
            String updateCareerFlag = "修改职业 ";
            if (message.startsWith(updateCareerFlag)) {
                StringBuilder stringBuilder = new StringBuilder(message);
                stringBuilder.delete(0, updateCareerFlag.length());
                String nickname = stringBuilder.substring(0, stringBuilder.indexOf(" "));
                String career = stringBuilder.substring(stringBuilder.indexOf(" ") + 1).trim();
                switch (career) {
                    case "牧师": {
                        career = "5";
                        break;
                    }
                    case "战士": {
                        career = "1";
                        break;
                    }
                    case "法师": {
                        career = "4";
                        break;
                    }
                    case "刺客": {
                        career = "2";
                        break;
                    }
                    case "射手": {
                        career = "3";
                        break;
                    }
                }
                if (cardService.updateCardCareer(nickname, career)) {
                    response.setReply("为 " + nickname + " 修改职业成功");
                    response.setFlag(true);
                    return response;
                }
            }

            // 修改性别
            String updateSexFlag = "修改性别 ";
            if (message.startsWith(updateSexFlag)) {
                StringBuilder stringBuilder = new StringBuilder(message);
                stringBuilder.delete(0, updateSexFlag.length());
                String nickname = stringBuilder.substring(0, stringBuilder.indexOf(" "));
                String sex = stringBuilder.substring(stringBuilder.indexOf(" ") + 1).trim();
                if (cardService.updateCardSex(nickname, sex)) {
                    response.setReply("为 " + nickname + " 修改性别 " + sex + " 成功");
                    response.setFlag(true);
                    return response;
                }
            }

            // 修改命运伙伴
            String updateFatePartnerFlag = "修改命运伙伴 ";
            if (message.startsWith(updateFatePartnerFlag)) {
                StringBuilder stringBuilder = new StringBuilder(message);
                stringBuilder.delete(0, updateFatePartnerFlag.length());
                String nickname = stringBuilder.substring(0, stringBuilder.indexOf(" "));
                String fatePartner = stringBuilder.substring(stringBuilder.indexOf(" ") + 1).trim();
                if (cardService.updateCardPartner(nickname, fatePartner)) {
                    response.setReply("为 " + nickname + " 修改命运伙伴 " + fatePartner + " 成功");
                    response.setFlag(true);
                    return response;
                }
            }

            // 修改喜欢的物品
            String updateFavourateFlag = "修改喜欢的物品 ";
            if (message.startsWith(updateFavourateFlag)) {
                StringBuilder stringBuilder = new StringBuilder(message);
                stringBuilder.delete(0, updateFavourateFlag.length());
                String nickname = stringBuilder.substring(0, stringBuilder.indexOf(" "));
                String favourate = stringBuilder.substring(stringBuilder.indexOf(" ") + 1).trim();
                if (cardService.updateCardFavourate(nickname, favourate)) {
                    response.setReply("为 " + nickname + " 修改喜欢的物品 " + favourate + " 成功");
                    response.setFlag(true);
                    return response;
                }
            }

            // 修改不喜欢的物品
            String updateDislikeFlag = "修改不喜欢的物品 ";
            if (message.startsWith(updateDislikeFlag)) {
                StringBuilder stringBuilder = new StringBuilder(message);
                stringBuilder.delete(0, updateDislikeFlag.length());
                String nickname = stringBuilder.substring(0, stringBuilder.indexOf(" "));
                String dislike = stringBuilder.substring(stringBuilder.indexOf(" ") + 1).trim();
                if (cardService.updateCardDislike(nickname, dislike)) {
                    response.setReply("为 " + nickname + " 修改不喜欢的物品 " + dislike + " 成功");
                    response.setFlag(true);
                    return response;
                }
            }

            // 修改别名
            String updateNicknameFlag = "修改别名 ";
            if (message.startsWith(updateNicknameFlag)) {
                StringBuilder stringBuilder = new StringBuilder(message);
                stringBuilder.delete(0, updateNicknameFlag.length());
                String nickname = stringBuilder.substring(0, stringBuilder.indexOf(" "));
                String newNickname = stringBuilder.substring(stringBuilder.indexOf(" ") + 1).trim();
                if (cardService.updateCardNickname(nickname, newNickname)) {
                    response.setReply("为 " + nickname + " 修改昵称 " + newNickname + " 成功");
                    response.setFlag(true);
                    return response;
                }
            }

            // 修改全名
            String updateFullnameFlag = "修改全名 ";
            if (message.startsWith(updateFullnameFlag)) {
                StringBuilder stringBuilder = new StringBuilder(message);
                stringBuilder.delete(0, updateFullnameFlag.length());
                String nickname = stringBuilder.substring(0, stringBuilder.indexOf(" "));
                String fullname = stringBuilder.substring(stringBuilder.indexOf(" ") + 1).trim();
                if (cardService.updateFullName(nickname, fullname, "多莉贝露")) {
                    response.setReply("为 " + nickname + " 修改全名 " + fullname + " 成功");
                    response.setFlag(true);
                    return response;
                }
            }
        }
        return response;
    }
}
