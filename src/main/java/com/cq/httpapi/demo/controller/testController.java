package com.cq.httpapi.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class testController {
//    @Resource
//    TowerService towerService;

//    @RequestMapping(value = "/test/imd")
//    public String testR() {
//        try {
//            String filePath = "C:\\Users\\Administrator\\Desktop\\QQbot\\pics\\data\\pics";
//            Workbook workbook = ExcelKit.getWorkbook("C:\\Users\\Administrator\\Desktop\\QQbot\\pics\\data\\问答条目.xlsx");
//            Sheet sheet = ExcelKit.getSheet(workbook, "Sheet1");
//            int rows = sheet.getLastRowNum();
//            for (int i = 1; i <= rows + 1; i++) {
//                Row row = sheet.getRow(i);
//                if (row == null) {
//                    continue;
//                }
//                Cell questionCell = row.getCell(0);
//                Cell answerCell = row.getCell(1);
//                if (questionCell != null) {
//                    String question = questionCell.getStringCellValue();
//                    if (question != null) {
//                        String answer;
//                        if (answerCell != null) {
//                            answer = answerCell.getStringCellValue();
//                        } else {
//                            answer = "暂无数据";
//                        }
//                        question = question.trim();
//                        answer = answer.trim();
//                        if (answer.equals("\\t")) {
//                            int tmpRowNum = i + 1;
//                            StringBuilder tmpAnswer = new StringBuilder("请选择\n");
//                            String tmpCellValue = "";
//                            boolean flag = false;
//                            do {
//                                Row tmpRow = sheet.getRow(tmpRowNum);
//                                if (tmpRow == null) {
//                                    tmpRowNum++;
//                                    continue;
//                                }
//                                Cell tmpCell = tmpRow.getCell(0);
//                                tmpCellValue = tmpCell.getStringCellValue();
//                                if (tmpCellValue == null) {
//                                    tmpRowNum++;
//                                    continue;
//                                } else if (!tmpCellValue.isEmpty()) {
//                                    if (tmpCellValue.contains("\\i")) {
//                                        flag = true;
//                                    } else {
//                                        tmpAnswer.append(tmpCellValue);
//                                        tmpAnswer.append("\n");
//                                    }
//                                }
//                                tmpRowNum++;
//                            } while (!tmpCellValue.contains("\\t"));
//                            String tail = flag ? "......" : "";
//                            if (tmpAnswer.indexOf("\\t") > 0) {
//                                tmpAnswer.replace(tmpAnswer.indexOf("\\t"), tmpAnswer.indexOf("\\t") + 2, "");
//                            }
//                            tmpAnswer.append(tail);
//                            answer = tmpAnswer.toString().trim();
//                        }
//
//                        if (answer.endsWith(".jpg") || answer.endsWith(".png") || answer.endsWith(".jpeg")) {
//                            answer = filePath + answer;
//                            answer = "[CQ:image,file=file:///" + answer + "]";
//                        }
//                        if (question.contains("\\t")) {
//                            StringBuilder tmpQuestion = new StringBuilder(question);
//                            tmpQuestion.replace(tmpQuestion.indexOf("\\t"), tmpQuestion.indexOf("\\t") + 2, "");
//                            question = tmpQuestion.toString();
//                        }
//                        if (question.contains("\\i")) {
//                            StringBuilder tmpQuestion = new StringBuilder(question);
//                            tmpQuestion.replace(tmpQuestion.indexOf("\\i"), tmpQuestion.indexOf("\\i") + 2, "");
//                            question = tmpQuestion.toString();
//                        }
//                        if (towerService.exist(question, "0")) {
//                            towerService.updateQuestion(question, answer, "0", "多莉贝露");
//                        } else {
//                            towerService.insertQuestion(question, answer, "0", "多莉贝露");
//                        }
//                    }
//                }
//            }
//            workbook.close();
//            return "true";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "false";
//        }

//    }
}
