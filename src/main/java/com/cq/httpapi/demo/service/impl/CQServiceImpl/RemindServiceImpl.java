package com.cq.httpapi.demo.service.impl.CQServiceImpl;

import com.cq.httpapi.demo.dao.CQdao.RemindDao;
import com.cq.httpapi.demo.entity.CQ.Remind;
import com.cq.httpapi.demo.service.CQService.RemindService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class RemindServiceImpl implements RemindService {

    @Resource
    private RemindDao remindDao;

    @Override
    public ArrayList<Remind> getAllUsableRemind() {
        ArrayList<Remind> reminds = remindDao.getAllUsableRemind();
        return reminds;
    }


    /**
     * 返回提醒的id, guild, message, remind_time
     *
     * @param minute
     * @return
     */
    @Override
    public ArrayList<Remind> getRemind(Long minute) {
        ArrayList<Remind> reminds = remindDao.getUsableRemindByTime(minute);
        return reminds;
    }

    @Override
    @Transactional
    public ArrayList<Remind> getRemindByGuild(String guild) {
        ArrayList<Remind> reminds = remindDao.getRemindInfoByGuild(guild);
        return reminds;
    }

    @Override
    public Remind getRemindById(String id) {
        Remind remind = remindDao.getRemindInfoById(id);
        return remind;
    }

    @Override
    public boolean updateRemindMessageById(String id, String message) {
        try {
            remindDao.updateRemindMessageById(id, message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateRemindGuildById(String id, String guild) {
        try {
            remindDao.updateRemindGuildById(id, guild);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updateRemindTimeById(String id, String time, String modifiedTime, String modifiedUserId, String desc) {
        try {
            remindDao.updateRemindTimeById(id, time);
            remindDao.updateRemindUpdateInfoById(id, Timestamp.valueOf(modifiedTime), modifiedUserId);
            remindDao.updateRemindDescriptionById(id, desc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createRemind(String guild, String message, String remindTime, String usable, String mode,
                                String createTime, String createUserId) {
        try {
            remindDao.insertRemind(guild, message, remindTime, usable, mode, createTime, createUserId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRemind(String id) {
        try {
            remindDao.deleteRemindById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
