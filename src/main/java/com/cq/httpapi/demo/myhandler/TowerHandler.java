package com.cq.httpapi.demo.myhandler;

import com.cq.httpapi.demo.dto.User;
import com.cq.httpapi.demo.dto.response.message.GroupMessageResponse;
import com.cq.httpapi.demo.dto.response.message.MessageResponse;
import com.cq.httpapi.demo.dto.response.message.PrivateMessageResponse;
import com.cq.httpapi.demo.entity.Tower;
import com.cq.httpapi.demo.handler.httphandler.message.GrpMsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.MsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.PriMsgHttpReqHandler;
import com.cq.httpapi.demo.kit.SenderKit;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.CQService.TowerService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

@Component
public class TowerHandler {

    @Resource
    private TowerService towerService;

    public MessageResponse towerHandler(MsgHttpReqHandler msgHttpReqHandler) {
        String message = msgHttpReqHandler.getMessage();
        MessageResponse response = null;
        String userId = null;

        if (GrpMsgHttpReqHandler.class.isInstance(msgHttpReqHandler)) {

            GrpMsgHttpReqHandler grpMsgHttpReqHandler = (GrpMsgHttpReqHandler) msgHttpReqHandler;

            response = new GroupMessageResponse();
            response.setFlag(false);

            userId = grpMsgHttpReqHandler.getGroupId();

            //查看是否存在问答
            try {
                String answer = towerService.getAnswer(message, userId);
                String answer2 = towerService.getAnswer(message, "0");
                // 优先查询局部问答，然后才查询全局问答
                // 优先查询问答，然后才查询好感度信息
                if (answer != null && !answer.isEmpty()) {
                    response.setReply(answer);
                    response.setFlag(true);
                    return response;
                } else if (answer2 != null && !answer2.isEmpty()) {
                    response.setReply(answer2);
                    response.setFlag(true);
                    return response;
                } else {
                    response.setFlag(false);
                }
            } catch (Exception e) {
                response.setFlag(false);
            }

            //查看是否是增加、删除、修改问答
            if (SenderKit.isAdminOrOwner(grpMsgHttpReqHandler)) {
                // 增加问答
                if (message.startsWith("查问 ")) {
                    try {
                        String questionFlag = "查问 ";
                        String answerFlag = "回答 ";
                        String optionTimeFlag = "-t";
                        String optionAllFlag = "-a";

                        StringBuilder stringBuilder = new StringBuilder(message);
                        // 删除 查问[空格]
                        stringBuilder.delete(0, questionFlag.length());
                        String question = stringBuilder.substring(0, stringBuilder.indexOf(answerFlag));
                        // 删除 问题
                        stringBuilder.delete(0, question.length());
                        // 删除 回答[空格]
                        stringBuilder.delete(0, answerFlag.length());

                        String answer;
                        if (message.indexOf(optionTimeFlag) > 0) {
                            answer = stringBuilder.substring(0, stringBuilder.indexOf(optionTimeFlag)).trim();
                            answer += "\n" + TimeKit.getFormalTime();
                        } else {
                            answer = stringBuilder.toString().trim();
                        }

                        // 若命令中存在 -a 选项且命令发送人为 502063298，
                        // 则删除 answer 中的 -a
                        // 并设置 guild = 0
                        if (message.contains(optionAllFlag) &&
                                SenderKit.CheckGrpMsgSenderId(grpMsgHttpReqHandler, User.DOLLYBELU.getUserId())) {
                            stringBuilder.delete(stringBuilder.indexOf(optionAllFlag), stringBuilder.indexOf(optionAllFlag) + optionAllFlag.length());
                            answer = stringBuilder.toString().trim();
                            userId = "0";
                        }

                        // 若数据库中存在问答，则修改答案
                        // 问题：全局问答和局部问答的关系
                        if (!towerService.exist(question, userId)) {
                            towerService.insertQuestion(question, answer, userId, SenderKit.GetGrpMsgSenderId(grpMsgHttpReqHandler));
                            response.setReply("添加问答成功！\n你可以这样问我：" + question + "\n我会这么回答：" + answer);
                            response.setFlag(true);
                        } else {
                            towerService.updateQuestion(question, answer, userId, SenderKit.GetGrpMsgSenderId(grpMsgHttpReqHandler));
                            response.setReply("修改问答成功！\n你可以这样问我：" + question + "\n我会这么回答：" + answer);
                            response.setFlag(true);
                        }

                    } catch (Exception e) {
                        response.setFlag(false);
                    }
                }

                //-------------------------------------------------------------------------------------
                //分段添加答案
//            if (message.startsWith("!查问 ")){
//                try{
//                    String questionFlag = "!查问 ";
//                    String answerEndFlag = "!";
//
//                    String question = message.substring(message.indexOf(questionFlag) + questionFlag.length());
//
//
//
//                    answer += "\n" + TimeKit.getFormalTime();
//
//                    if (!towerService.exist(question, guild)){
//                        towerService.insertQuestion(question, answer, guild, SenderKit.GetGrpMsgSenderId(grpMsgHttpReqHandler));
//                        response.setReply("添加问答成功！\n你可以这样问我：" + question + "\n我会这么回答：" + answer);
//                        response.setFlag(true);
//                    }
//                    else {
//                        towerService.updateQuestion(question, answer, guild, SenderKit.GetGrpMsgSenderId(grpMsgHttpReqHandler));
//                        response.setReply("修改问答成功！\n你可以这样问我：" + question + "\n我会这么回答：" + answer);
//                        response.setFlag(true);
//                    }
//
//                } catch (Exception e){
//                    response.setFlag(false);
//                }
//            }

                if (message.startsWith("删除问答 ")) {
                    try {
                        String questionFlag = "删除问答 ";
                        StringBuilder stringBuilder = new StringBuilder(message);
                        stringBuilder.delete(0, questionFlag.length());
                        String question = stringBuilder.toString();

                        // 如果包含 -a 并且 发送人是 多莉贝露
                        // 去掉问题中的 -a
                        // 删除全局问答
                        if (question.endsWith("-a") &&
                                SenderKit.CheckGrpMsgSenderId(grpMsgHttpReqHandler, User.DOLLYBELU.getUserId())) {
                            question = question.substring(0, question.indexOf("-a"));
                            userId = "0";
                        }
                        if (towerService.deleteQuestion(question, userId)) {
                            if (message.contains("-a")) {
                                response.setReply("删除全局问答成功！我已经忘了该怎么回答 " + question + " 了");
                                response.setFlag(true);
                                return response;
                            } else {
                                response.setReply("删除问答成功！我已经忘了该怎么回答 " + question + " 了");
                                response.setFlag(true);
                                return response;
                            }
                        }
                    } catch (Exception e) {
                        response.setFlag(false);
                    }
                }

                if (message.equals("查看所有问答")) {
                    try {
                        String guild = ((GrpMsgHttpReqHandler) msgHttpReqHandler).getGroupId();
                        ArrayList<Tower> questionList = towerService.getQuestionList(guild);
                        StringBuilder questions = new StringBuilder();
                        for (Tower tower : questionList) {
                            questions.append(tower.getQuestion());
                            questions.append("\n");
                        }
                        response.setReply(questions.toString().trim());
                        response.setFlag(true);
                        return response;
                    } catch (Exception e) {
                        response.setFlag(false);
                    }
                }
            }

            return response;

        } else if (PriMsgHttpReqHandler.class.isInstance(msgHttpReqHandler)) {

            PriMsgHttpReqHandler priMsgHttpReqHandler = (PriMsgHttpReqHandler) msgHttpReqHandler;

            response = new PrivateMessageResponse();
            response.setFlag(false);

            userId = SenderKit.GetMsgSenderId(priMsgHttpReqHandler);

            // 查看是否是已存在的问答
            try {
                String answer = towerService.getAnswer(message, "p" + userId);
                if (answer != null && !answer.isEmpty()) {
                    response.setReply(answer);
                    response.setFlag(true);
                    return response;
                }
            } catch (Exception e) {
                response.setFlag(false);
            }

            String addTowerFlag = "查问 ";
            String answerFlag = "回答 ";

            // 新增问答
            if (message.startsWith(addTowerFlag)) {

                StringBuilder stringBuilder = new StringBuilder(message);

                // 删除 查问[空格]
                stringBuilder.delete(0, addTowerFlag.length());
                String question = stringBuilder.substring(0, stringBuilder.indexOf(answerFlag));
                // 删除 {question}
                stringBuilder.delete(0, question.length());
                // 删除 回答[空格]
                stringBuilder.delete(0, answerFlag.length());

                String answer = stringBuilder.toString();

                // 若数据库中存在问答，则修改答案
                if (!towerService.exist(question, "p" + userId)) {
                    towerService.insertQuestion(question, answer, "p" + userId, userId);
                    response.setReply("添加问答成功！\n你可以这样问我：" + question + "\n我会这么回答：" + answer);
                    response.setFlag(true);
                    return response;
                } else {
                    towerService.updateQuestion(question, answer, "p" + userId, userId);
                    response.setReply("修改问答成功！\n你可以这样问我：" + question + "\n我会这么回答：" + answer);
                    response.setFlag(true);
                    return response;
                }
            }

            // 删除问答
            String deleteTowerFlag = "删除问答 ";
            if (message.startsWith(deleteTowerFlag)) {
                try {
                    StringBuilder stringBuilder = new StringBuilder(message);
                    stringBuilder.delete(0, deleteTowerFlag.length());
                    String question = stringBuilder.toString();

                    if (towerService.deleteQuestion(question, "p" + userId)) {
                        response.setReply("删除问答成功！我已经忘了该怎么回答 " + question + " 了");
                        response.setFlag(true);
                        return response;
                    }
                } catch (Exception e) {
                    response.setFlag(false);
                }
            }

            if (message.equals("查看所有问答")) {
                try {
                    userId = "p" + userId;
                    ArrayList<Tower> questionList = towerService.getQuestionList(userId);
                    StringBuilder questions = new StringBuilder();
                    for (Tower tower : questionList) {
                        questions.append(tower.getQuestion());
                        questions.append("\n");
                    }
                    response.setReply(questions.toString().trim());
                    response.setFlag(true);
                    return response;
                } catch (Exception e) {
                    response.setFlag(false);
                }
            }

            return response;
        }


        return null;
    }
}
