package com.cq.httpapi.demo.myhandler;

import com.alibaba.fastjson.JSONArray;
import com.cq.httpapi.demo.dto.User;
import com.cq.httpapi.demo.dto.response.message.GroupMessageResponse;
import com.cq.httpapi.demo.dto.response.message.PrivateMessageResponse;
import com.cq.httpapi.demo.handler.httphandler.message.GrpMsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.PriMsgHttpReqHandler;
import com.cq.httpapi.demo.kit.CQKit.CQSenderKit;
import com.cq.httpapi.demo.kit.ExcelKit;
import com.cq.httpapi.demo.kit.SQLKit;
import com.cq.httpapi.demo.service.CQService.BloodlineEquipmentService;
import com.cq.httpapi.demo.service.CQService.BloodlineItemService;
import com.cq.httpapi.demo.service.CQService.CardService;
import com.cq.httpapi.demo.service.CQService.TowerService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ImportHandler {

    @Resource
    private BloodlineItemService itemService;
    @Resource
    private BloodlineEquipmentService equipmentService;
    @Resource
    private TowerService towerService;

    public static GroupMessageResponse importHandler(GrpMsgHttpReqHandler grpMsgHttpReqHandler, CardService cardService) {
        String msg = grpMsgHttpReqHandler.getMessage();
        GroupMessageResponse groupMessageResponse = new GroupMessageResponse();
        groupMessageResponse.setFlag(false);
        if (msg.equals("import") && CQSenderKit.CheckGrpMsgSenderId(grpMsgHttpReqHandler, User.DOLLYBELU.getUserId())) {
//            JSONArray jsonArray = SQLKit.getCardData("C:\\Users\\Administrator\\Desktop\\2019-3-27Data");
            JSONArray jsonArray = SQLKit.getCardData("C:\\Users\\Public\\Downloads\\bloodline\\CardData");
            SQLKit.importDataToDB(cardService, jsonArray);
        }
        return groupMessageResponse;
    }

    public GroupMessageResponse importItems(GrpMsgHttpReqHandler grpMsgHttpReqHandler) {
        String msg = grpMsgHttpReqHandler.getMessage();
        GroupMessageResponse groupMessageResponse = new GroupMessageResponse();
        groupMessageResponse.setFlag(false);
        if (msg.equals("import") && CQSenderKit.CheckGrpMsgSenderId(grpMsgHttpReqHandler, User.DOLLYBELU.getUserId())) {
            try {
                Workbook workbook = ExcelKit.getWorkbook("C:\\Users\\Public\\Downloads\\bloodline\\血族资料整合集1.26.xlsx");
                Sheet sheet = ExcelKit.getSheet(workbook, "100装备表");
                for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                    Row row = sheet.getRow(i);
                    Cell flag = row.getCell(0);
                    if (flag != null) {
                        String equipName = row.getCell(0).getStringCellValue();
                        String equipType = row.getCell(2).getStringCellValue();
                        String cardName = row.getCell(3).getStringCellValue();
                        String description = row.getCell(7).getStringCellValue();
                        itemService.createItem(equipName, "装备", description);
//                        System.err.println("createItem: " + equipName + " 装备 " + description);
                        equipType = equipType.substring(0, 1) + equipType.substring(equipType.length() - 1);
                        if (cardName.contains("/")) {
                            String[] cardNames = cardName.split("/");
                            for (String name : cardNames) {
//                                System.err.println("createEquipment: " + name + equipName + equipType);
                                equipmentService.createEquipmentRecord(name.trim(), equipName, equipType);
                            }
                        } else {
//                            System.err.println("createEquipment: " + cardName + equipName + equipType);
                            equipmentService.createEquipmentRecord(cardName, equipName, equipType);
                        }
                    }
                }
                groupMessageResponse.setFlag(true);
                groupMessageResponse.setReply("Successfully import!");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return groupMessageResponse;
    }

    public PrivateMessageResponse importMaxData(PriMsgHttpReqHandler priMsgHttpReqHandler) {

        String message = priMsgHttpReqHandler.getMessage();
        PrivateMessageResponse response = new PrivateMessageResponse();
        response.setFlag(false);
        if (message.equals("importMaxData")) {
            try {
                String filePath = "C:\\Users\\Public\\Downloads\\bloodline\\data\\pics";
                Workbook workbook = ExcelKit.getWorkbook("C:\\Users\\Public\\Downloads\\bloodline\\data\\问答条目.xlsx");
//                String filePath = "C:\\Users\\Administrator\\Desktop\\QQbot\\pics\\data\\pics";
//                Workbook workbook = ExcelKit.getWorkbook("C:\\Users\\Administrator\\Desktop\\QQbot\\pics\\data\\问答条目.xlsx");
                Sheet sheet = ExcelKit.getSheet(workbook, "Sheet1");
                int rows = sheet.getLastRowNum();
                for (int i = 1; i <= rows + 1; i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }
                    Cell questionCell = row.getCell(0);
                    Cell answerCell = row.getCell(1);
                    if (questionCell != null) {
                        String question = questionCell.getStringCellValue();
                        if (question != null) {
                            String answer;
                            if (answerCell != null) {
                                answer = answerCell.getStringCellValue();
                            } else {
                                answer = "暂无数据";
                            }
                            question = question.trim();
                            answer = answer.trim();
                            if (answer.equals("\\t")) {
                                int tmpRowNum = i + 1;
                                StringBuilder tmpAnswer = new StringBuilder("请选择\n");
                                String tmpCellValue = "";
                                boolean flag = false;
                                do {
                                    Row tmpRow = sheet.getRow(tmpRowNum);
                                    if (tmpRow == null) {
                                        tmpRowNum++;
                                        continue;
                                    }
                                    Cell tmpCell = tmpRow.getCell(0);
                                    tmpCellValue = tmpCell.getStringCellValue();
                                    if (tmpCellValue == null) {
                                        tmpRowNum++;
                                        continue;
                                    } else if (!tmpCellValue.isEmpty()) {
                                        if (tmpCellValue.contains("\\i")) {
                                            flag = true;
                                        } else {
                                            tmpAnswer.append(tmpCellValue);
                                            tmpAnswer.append("\n");
                                        }
                                    }
                                    tmpRowNum++;
                                } while (!tmpCellValue.contains("\\t"));
                                String tail = flag ? "......" : "";
                                if (tmpAnswer.indexOf("\\t") > 0) {
                                    tmpAnswer.replace(tmpAnswer.indexOf("\\t"), tmpAnswer.indexOf("\\t") + 2, "");
                                }
                                tmpAnswer.append(tail);
                                answer = tmpAnswer.toString().trim();
                            }

                            if (answer.endsWith(".jpg") || answer.endsWith(".png") || answer.endsWith(".jpeg")) {
                                answer = filePath + answer;
                                answer = "[CQ:image,file=file:///" + answer + "]";
                            }
                            if (question.contains("\\t")) {
                                StringBuilder tmpQuestion = new StringBuilder(question);
                                tmpQuestion.replace(tmpQuestion.indexOf("\\t"), tmpQuestion.indexOf("\\t") + 2, "");
                                question = tmpQuestion.toString();
                            }
                            if (question.contains("\\i")) {
                                StringBuilder tmpQuestion = new StringBuilder(question);
                                tmpQuestion.replace(tmpQuestion.indexOf("\\i"), tmpQuestion.indexOf("\\i") + 2, "");
                                question = tmpQuestion.toString();
                            }
                            if (towerService.exist(question, "0")) {
                                towerService.updateQuestion(question, answer, "0", "多莉贝露");
                            } else {
                                towerService.insertQuestion(question, answer, "0", "多莉贝露");
                            }
                        }
                    }
                }
                response.setFlag(true);
                response.setReply("导入极限数据成功");
                workbook.close();
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                response.setFlag(false);
                return response;
            }

        }


        return response;
    }
}
