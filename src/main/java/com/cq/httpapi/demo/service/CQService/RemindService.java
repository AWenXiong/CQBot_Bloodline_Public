package com.cq.httpapi.demo.service.CQService;

import com.cq.httpapi.demo.entity.Remind;

import java.util.ArrayList;

public interface RemindService {

    /**
     * 获取所有提醒
     *
     * @return
     */
    ArrayList<Remind> getAllUsableRemind();

    /**
     * 获取接下来 minute 分钟内可用提醒 Remind
     *
     * @param minute
     * @return
     */
    ArrayList<Remind> getRemind(Long minute);

    /**
     * 根据公会号获取该公会所有Remind的信息
     *
     * @param guild
     * @return
     */
    ArrayList<Remind> getRemindByGuild(String guild);

    /**
     * 根据 id 获得对应的Remind
     *
     * @param id
     * @return
     */
    Remind getRemindById(String id);

    /**
     * 根据记录id修改提醒信息
     *
     * @param id
     * @param message
     * @return
     */
    boolean updateRemindMessageById(String id, String message);

    /**
     * 根据记录id修改需要提醒的公会
     *
     * @param id
     * @param guild
     * @return
     */
    boolean updateRemindGuildById(String id, String guild);

    /**
     * 根据记录id修改提醒时间
     *
     * @param id
     * @param time
     * @param modifiedTime
     * @param modifiedUserId
     * @param desc
     * @return
     */
    boolean updateRemindTimeById(String id,
                                 String time,
                                 String modifiedTime,
                                 String modifiedUserId,
                                 String desc);


    /**
     * 创建新的提醒
     *
     * @param guild
     * @param message
     * @param remindTime
     * @param usable
     * @param mode
     * @param createTime
     * @param createUserId
     * @return
     */
    boolean createRemind(String guild,
                         String message,
                         String remindTime,
                         int usable,
                         String mode,
                         String createTime,
                         String createUserId);

    /**
     * 根据记录id删除提醒
     *
     * @param id
     * @return
     */
    boolean deleteRemind(String id);
}
