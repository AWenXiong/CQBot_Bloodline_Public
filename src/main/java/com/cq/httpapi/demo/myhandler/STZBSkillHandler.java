package com.cq.httpapi.demo.myhandler;

import com.cq.httpapi.demo.annotation.cqannotation.CQResponse;
import com.cq.httpapi.demo.config.Account;
import com.cq.httpapi.demo.dto.response.message.MessageResponse;
import com.cq.httpapi.demo.dto.send.SendGroupMessage;
import com.cq.httpapi.demo.entity.STZB.StzbSkill;
import com.cq.httpapi.demo.handler.httphandler.message.GrpMsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.MsgHttpReqHandler;
import com.cq.httpapi.demo.kit.ObjectKit;
import com.cq.httpapi.demo.service.STZBService.StzbSkillService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class STZBSkillHandler {

    @Resource
    StzbSkillService stzbSkillService;

    // 对技能表进行查
    @CQResponse
    public MessageResponse skillChecker(MsgHttpReqHandler msgHttpReqHandler) {
        String message = msgHttpReqHandler.getMessage();
        MessageResponse response = ObjectKit.getCQMessageResponse(msgHttpReqHandler);

        // 查询卡的好感信息
        String hobbyKeyWord = "效果";
        if (message.endsWith(hobbyKeyWord)) {
            int endIndex = message.indexOf(hobbyKeyWord);
            String skillName = message.substring(0, endIndex);
            if (stzbSkillService.existSkill(skillName)) {
                StzbSkill skill = stzbSkillService.getSkillByName(skillName);
                StringBuilder reply = new StringBuilder();
                reply.append(skillName + " 效果：\n");
                reply.append(skill.getSkillDesc() + "\n");
                reply.append(skill.getSkillType() + "  " + skill.getSkillDist() + "  " + skill.getSkillTarget() + "\n");
                if (skill.getSkillCond().equals("无") && skill.getSkillPs().equals("无")) {
                    reply.append("武将自带战法");
                } else {
                    reply.append("适用：" + skill.getSkillSoldierType() + "\n");
                    reply.append(skill.getSkillCond() + "\n");
                    reply.append(skill.getSkillPs());
                }
                SendGroupMessage sendGroupMessage1 = new SendGroupMessage();
                sendGroupMessage1.setIp(Account.getIpById(msgHttpReqHandler.getSelfId()));
                sendGroupMessage1.setGroup_id(((GrpMsgHttpReqHandler) msgHttpReqHandler).getGroupId());
                sendGroupMessage1.setMessage(reply.toString());
                String[] split = {"战法类型："};
                sendGroupMessage1.setSplitStrings(split);
                sendGroupMessage1.executeWithSplitWord();
                return response;
            }
        }
        return response;
    }

}
