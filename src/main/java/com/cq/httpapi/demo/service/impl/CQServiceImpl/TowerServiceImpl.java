package com.cq.httpapi.demo.service.impl.CQServiceImpl;

import com.cq.httpapi.demo.dao.CQdao.TowerDao;
import com.cq.httpapi.demo.entity.CQ.Tower;
import com.cq.httpapi.demo.exception.DeleteTowerException;
import com.cq.httpapi.demo.exception.QANotUsablelException;
import com.cq.httpapi.demo.exception.UpdateTowerException;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.CQService.TowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class TowerServiceImpl implements TowerService {

    @Resource
    private TowerDao towerDao;

    @Override
    public String getAnswer(String question, String guild) throws QANotUsablelException {
        try {
            if (this.isUsable(question, guild)) {
                if (this.exist(question, guild)) {
                    Tower tower = towerDao.getTowerIdByQuestionAndGuild(question, guild);
                    return towerDao.getAnswerById(tower.getId());
                } else {
                    return "";
                }
            } else {
                throw new QANotUsablelException();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String insertQuestion(String question, String answer, String guild, String modifierId) {
        try {
            if (this.isUsable(question, guild)) {
                towerDao.insertAnswer(question, answer, guild);
                Tower tower = towerDao.getTowerIdByQuestionAndGuild(question, guild);
                towerDao.updateDescription(tower.getId(), "新增问答");
                towerDao.updateModifierInfo(tower.getId(), TimeKit.getFormalTime(), modifierId);
                return "Success Add";
            } else {
                throw new QANotUsablelException();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteQuestion(String question, String guild) {
        try {
            if (this.isUsable(question, guild)) {
                if (this.exist(question, guild)) {
                    Tower tower = towerDao.getTowerIdByQuestionAndGuild(question, guild);
                    towerDao.deleteTower(tower.getId());
                    return true;
                } else {
                    throw new DeleteTowerException();
                }
            } else {
                throw new QANotUsablelException();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String updateQuestion(String question, String answer, String guild, String modifierId) {
        try {
            if (this.isUsable(question, guild)) {
                if (this.exist(question, guild)) {
                    Tower tower = towerDao.getTowerIdByQuestionAndGuild(question, guild);
                    towerDao.updateDescription(tower.getId(), "修改答案");
                    towerDao.updateModifierInfo(tower.getId(), TimeKit.getFormalTime(), modifierId);
                    towerDao.updateAnswer(tower.getId(), answer);
                    return "Success Update";
                } else {
                    throw new UpdateTowerException();
                }
            } else {
                throw new QANotUsablelException();
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean isUsable(String question, String guild) {
        return true;
//        try{
//            Long flag = towerDao.isUsable(question, guild);
//            if (flag.intValue() == 1){
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
    }

    @Override
    public boolean exist(String question, String guild) {
        try {
            Integer flag = towerDao.existQuestion(question, guild);
            if (flag.intValue() != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean existGlobal(String question) {
        try {
            Integer flag = towerDao.existGlobalQuestion(question);
            if (flag.intValue() != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ArrayList<Tower> getQuestionList(String guild) {
        try {
            ArrayList<Tower> questionList = towerDao.getQuestionList(guild);
            return questionList;
        } catch (Exception e) {
            throw e;
        }
    }
}
