package com.cq.httpapi.demo.service.CQService;

import com.cq.httpapi.demo.entity.CQ.Tower;

import java.util.ArrayList;

public interface TowerService {

    String getAnswer(String question, String guild);

    String updateQuestion(String question, String answer, String guild, String modifierId);

    boolean deleteQuestion(String question, String guild);

    String insertQuestion(String question, String answer, String guild, String modifierId);

    boolean exist(String question, String guild);

    boolean existGlobal(String question);

    ArrayList<Tower> getQuestionList(String guild);
}
