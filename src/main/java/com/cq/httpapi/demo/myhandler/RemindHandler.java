package com.cq.httpapi.demo.myhandler;

import com.cq.httpapi.demo.annotation.cqannotation.CQResponse;
import com.cq.httpapi.demo.dto.User;
import com.cq.httpapi.demo.dto.response.message.GroupMessageResponse;
import com.cq.httpapi.demo.dto.response.message.PrivateMessageResponse;
import com.cq.httpapi.demo.dto.send.SendGroupMessage;
import com.cq.httpapi.demo.entity.Remind;
import com.cq.httpapi.demo.exception.InvalidRemindOptionException;
import com.cq.httpapi.demo.exception.TooManyOptionsException;
import com.cq.httpapi.demo.handler.httphandler.message.GrpMsgHttpReqHandler;
import com.cq.httpapi.demo.handler.httphandler.message.MsgHttpReqHandler;
import com.cq.httpapi.demo.kit.*;
import com.cq.httpapi.demo.service.RemindService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class RemindHandler {

    // All options
    final static String remindModeDefaultFlag = "-o";
    //    public static void setRemindService(RemindService remindService1) {
//        remindService = remindService1;
//    }
    final static String onceFlag = "-o";
    final static String everyFlag = "-e";
    final static String weekdayFlag = "-w";
    final static String delayFlag = "-d";
    final static String messageOptionDefaultFlag = "-n";
    final static String atAllFlag = "-a";
    final static String noTranslateFlag = "-n";
    final static String atManagerFlag = "-m";
    // TimeZone options
    final static String TimeZone_Default = "GMT+8";
    final static String TimeZone_BJ = "-BJ";
    final static String TimeZone_LA = "-LA";
    private static Timer checkScheduleTimer = null;
    private static TimerTask checkScheduleTimerTask = null;
    private static String prodTimeZone = "GMT+8";
    private static boolean remindStartFlag = false;
    //    private static RemindService remindService = null;
    @Resource
    private RemindService remindService;

//    private static final RemindHandler instance = new RemindHandler();
//    private RemindHandler() {
//        System.err.println("Initialize RemindHandler successfully!");
//    }

    /**
     * 手动启动任务
     * 口令 启动提醒
     *
     * @param msgHttpReqHandler
     * @return
     */
    @CQResponse
//    public static PrivateMessageResponse startCheckSchedule(MsgHttpReqHandler msgHttpReqHandler) {
    public PrivateMessageResponse startCheckSchedule(MsgHttpReqHandler msgHttpReqHandler) {
        PrivateMessageResponse privateMessageResponse = new PrivateMessageResponse();
        String msg = msgHttpReqHandler.getMessage();
        if (!remindStartFlag && msg.equals("启动提醒") &&
                SenderKit.CheckMsgSenderId(msgHttpReqHandler, User.DOLLYBELU.getUserId())) {
            try {
                startCheckSchedule(remindService);
                privateMessageResponse.setFlag(true);
                privateMessageResponse.setReply("启动提醒成功!");
                remindStartFlag = true;
            } catch (Exception e) {
//                e.printStackTrace();
            }
        } else {
            privateMessageResponse.setFlag(false);
        }
        return privateMessageResponse;
    }

    /**
     * 手动停止提醒任务
     * 口令 停止提醒
     *
     * @param msgHttpReqHandler
     * @return
     */
    @CQResponse
//    public static PrivateMessageResponse stopCheckSchedule(MsgHttpReqHandler msgHttpReqHandler) {
    public PrivateMessageResponse stopCheckSchedule(MsgHttpReqHandler msgHttpReqHandler) {
        PrivateMessageResponse privateMessageResponse = new PrivateMessageResponse();
        String msg = msgHttpReqHandler.getMessage();
        if (msg.equals("停止提醒") && SenderKit.CheckMsgSenderId(msgHttpReqHandler, User.DOLLYBELU.getUserId())) {
            try {
                stopCheckSchedule();
                privateMessageResponse.setFlag(true);
                privateMessageResponse.setReply("停止提醒成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            privateMessageResponse.setFlag(false);
        }
        return privateMessageResponse;
    }

    /**
     * 手动重启提醒任务
     * 口令 重启提醒
     *
     * @param msgHttpReqHandler
     * @return
     */
    @CQResponse
//    public static PrivateMessageResponse restartCheckSchedule(MsgHttpReqHandler msgHttpReqHandler) {
    public PrivateMessageResponse restartCheckSchedule(MsgHttpReqHandler msgHttpReqHandler) {
        PrivateMessageResponse privateMessageResponse = new PrivateMessageResponse();
        String msg = msgHttpReqHandler.getMessage();
        if (msg.equals("重启提醒") && SenderKit.CheckMsgSenderId(msgHttpReqHandler, User.DOLLYBELU.getUserId())) {
            try {
                restartCheckSchedule(remindService);
                privateMessageResponse.setFlag(true);
                privateMessageResponse.setReply("重启提醒成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            privateMessageResponse.setFlag(false);
        }
        return privateMessageResponse;
    }

    /**
     * 启动提醒任务
     *
     * @param remindService
     */
//    private static void startCheckSchedule(RemindService remindService) {
    private void startCheckSchedule(RemindService remindService) {
        if (checkScheduleTimer == null) {
            checkScheduleTimer = new Timer();
        }

        if (checkScheduleTimerTask == null) {
            checkScheduleTimerTask = new TimerTask() {
                @Override
                public void run() {
                    checkSchedule(remindService);
                }
            };
        }

        if (checkScheduleTimer != null && checkScheduleTimerTask != null) {
            checkScheduleTimer.scheduleAtFixedRate(checkScheduleTimerTask, 0, 5 * 60 * 1000);
        }
    }

    /**
     * 停止提醒任务
     */
//    private static void stopCheckSchedule() {
    private void stopCheckSchedule() {
        if (checkScheduleTimer != null) {
            checkScheduleTimer.cancel();
            checkScheduleTimer = null;
        }

        if (checkScheduleTimerTask != null) {
            checkScheduleTimerTask.cancel();
            checkScheduleTimerTask = null;
        }
    }

    /**
     * 重启提醒任务
     *
     * @param remindService
     */
//    private static void restartCheckSchedule(RemindService remindService) {
    private void restartCheckSchedule(RemindService remindService) {
        stopCheckSchedule();
        startCheckSchedule(remindService);
    }

    /**
     * 每五分钟执行一次
     * 检查数据库中接下来五分钟需要执行的提醒，并将提醒加入到队列中
     * 更新下次进行提醒的时间
     *
     * @param remindService
     */
//    private static void checkSchedule(RemindService remindService) {
    private void checkSchedule(RemindService remindService) {
        ArrayList<Remind> reminds = remindService.getRemind(5L);

        for (Remind remind : reminds) {
            try {
                Timer timer = new Timer();
                // 设置定时任务
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        SendGroupMessage sendGroupMessage = new SendGroupMessage();
                        sendGroupMessage.setGroup_id(remind.getGuild());
                        sendGroupMessage.setMessage(remind.getMessage());
                        sendGroupMessage.execute();
                    }
                }, TimeKit.parseTime(remind.getRemindTime()));

                /*TODO*/
                // 这里可以封装为一个方法，以后可以顺便把 -o 的 usable 更新为 0
                // 更新这个提醒
//                Remind nextRemind = RemindHandler.nextRemind(remind);
                Remind nextRemind = nextRemind(remind);
                remindService.updateRemindTimeById(
                        Long.toString(remind.getId()),
                        nextRemind.getRemindTime(),    // update remind_time
                        TimeKit.getFormalTime(),       // update modified_time
                        "RemindHandler",// update modified_user_id
                        "更新提醒时间"    // update description
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给定一个提醒，计算下一次需要进行提醒的时间
     *
     * @param remind
     * @return 下次进行提醒的 Date
     */
//    private static Remind nextRemind(Remind remind) {
    private Remind nextRemind(Remind remind) {
        Remind res = new Remind();

        String mode = remind.getMode();
        String remindTime = remind.getRemindTime();
        if (mode.startsWith(everyFlag)) {
            int interval = Integer.parseInt(mode.substring(everyFlag.length()));
            Date nextRemindDate = TimeKit.getDate(remindTime, interval);
            res.setRemindTime(TimeKit.parseTime(nextRemindDate));
        } else if (mode.startsWith(weekdayFlag)) {
            int weekday = Integer.parseInt(mode.substring(weekdayFlag.length()));
            Date nextRemindDate = TimeKit.getNextWeekDay(remindTime, weekday);
            res.setRemindTime(TimeKit.parseTime(nextRemindDate));
        } else if (mode.startsWith(delayFlag)) {
            int internal = Integer.parseInt(mode.substring(delayFlag.length()));
            StringBuilder nextRemindTime = new StringBuilder(TimeKit.parseTime(TimeKit.getDate(internal)));
            nextRemindTime.replace(nextRemindTime.indexOf(" "),
                    nextRemindTime.length(), remindTime.substring(remindTime.indexOf(" ")));
            res.setRemindTime(nextRemindTime.toString());
        } else if (mode.equals(onceFlag)) {
            res.setRemindTime(remindTime);
        }

        return res;
    }

    /**
     * 设置提醒
     *
     * @param grpMsgHttpReqHandler
     * @return
     */
    @CQResponse
//    public static GroupMessageResponse setRemind(GrpMsgHttpReqHandler grpMsgHttpReqHandler) {
    public GroupMessageResponse setRemind(GrpMsgHttpReqHandler grpMsgHttpReqHandler) {
        GroupMessageResponse response = new GroupMessageResponse();
        response.setFlag(false);

        String msg = grpMsgHttpReqHandler.getMessage();
        String remindFlag = "设置提醒 ";
        if (msg.startsWith(remindFlag) && SenderKit.isAdminOrOwner(grpMsgHttpReqHandler)) {

            // 各种变量的声明
            StringBuilder msgBuilder = new StringBuilder(msg.trim());
            StringBuilder remindTime = new StringBuilder();
            StringBuilder remindMsg = new StringBuilder();
            String mode;
            String messageOption;
            String timeZoneOption;
            String timeZone;
            String timeZoneReply;

            try {
                // 获取 mode
                // mode 的默认值是 onceFlag , 即 -o
                mode = OptionKit.selectOption(msg, remindModeDefaultFlag,
                        onceFlag, everyFlag, weekdayFlag, delayFlag);
                // 获取 messageOption
                // messageOption 的默认值是 noTranslateFlag , 即 -n
                messageOption = OptionKit.selectOption(msg, messageOptionDefaultFlag,
                        atAllFlag, noTranslateFlag, atManagerFlag);
                // 获取 timeZone
                // timeZone 的默认值是 Beijing , 即 -BJ
                timeZoneOption = OptionKit.selectOption(msg, TimeZone_Default,
                        TimeZone_BJ, TimeZone_LA);

                // 获取 mode 的具体值，并判断值是否合法
                switch (mode) {
                    // -eX X为天数，每X天提醒一次
                    case everyFlag: {
                        String tmp = msg.substring(msg.indexOf(everyFlag));
                        if (tmp.indexOf(" ") > 0) {
                            mode = tmp.substring(0, tmp.indexOf(" "));
                        } else {
                            mode = tmp;
                        }
                        int internal = Integer.parseInt(mode.substring(2, mode.length()));
                        if (internal < 1) {
                            throw new InvalidRemindOptionException("-e选项的取值小于1");
                        }
                        break;
                    }

                    // -wX X为星期几，每星期X提醒一次
                    case weekdayFlag: {
                        String tmp = msg.substring(msg.indexOf(weekdayFlag));
                        if (tmp.indexOf(" ") > 0) {
                            mode = tmp.substring(0, tmp.indexOf(" "));
                        } else {
                            mode = tmp;
                        }
                        int weekday = Integer.parseInt(mode.substring(2, mode.length()));
                        if (weekday > 7) {
                            throw new InvalidRemindOptionException("-w选项取值大于7");
                        } else if (weekday < 1) {
                            throw new InvalidRemindOptionException("-w选项取值小于1");
                        }
                        break;
                    }

                    // -dX X为几天，延迟X天提醒一次
                    case delayFlag: {
                        String tmp = msg.substring(msg.indexOf(delayFlag));
                        if (tmp.indexOf(" ") > 0) {
                            mode = tmp.substring(0, tmp.indexOf(" "));
                        } else {
                            mode = tmp;
                        }
                        int delayDay = Integer.parseInt(mode.substring(2, mode.length()));
                        if (delayDay < 1) {
                            throw new InvalidRemindOptionException("-d选项取值小于1");
                        }
                        break;
                    }
                }

                // 获取 messageOption 的具体值
                switch (messageOption) {
                    // 艾特全体
                    case atAllFlag: {
                        remindMsg.append(CQCodeKit.atSomebody("all"));
                        remindMsg.append(" ");
                        break;
                    }

                    // 艾特全体管理
                    case atManagerFlag: {
                        ArrayList<String> admins = CQGroupKit.getGroupAdmin(grpMsgHttpReqHandler.getGroupId());
                        for (String admin : admins) {
                            remindMsg.append(CQCodeKit.atSomebody(admin));
                            remindMsg.append(" ");
                        }
                        break;
                    }
                }

                // 根据timeZoneOption 设置相应的 timeZone 以及相应的 timeZoneReply
                switch (timeZoneOption) {
                    case TimeZone_BJ: {
                        timeZone = "GMT+8";
                        timeZoneReply = "北京时间";
                        break;
                    }

                    case TimeZone_LA: {
                        timeZone = "America/Los_Angeles";
                        timeZoneReply = "洛杉矶时间";
                        break;
                    }

                    default: {
                        timeZone = "GMT+8";
                        timeZoneReply = "北京时间";
                        break;
                    }
                }

                // 将msg中的 mode , messageOption , timeZoneOption 删掉
                int modeIndex = msgBuilder.indexOf(mode);
                if (modeIndex != -1) {
                    msgBuilder.replace(modeIndex, modeIndex + mode.length(), "");
                }
                int messageOptionIndex = msgBuilder.indexOf(messageOption);
                if (messageOptionIndex != -1) {
                    msgBuilder.replace(messageOptionIndex, messageOptionIndex + messageOption.length(), "");
                }
                int timeZoneIndex = msgBuilder.indexOf(timeZoneOption);
                if (timeZoneIndex != -1) {
                    msgBuilder.replace(timeZoneIndex, timeZoneIndex + timeZoneOption.length(), "");
                }

                // 处理后的 msgBuilder 格式为 设置提醒[空格]{remindTime}[空格]{remindMsg}
                msgBuilder.replace(0, msgBuilder.length(), msgBuilder.toString().trim());

                // 删除 设置提醒[空格]
                msgBuilder.delete(0, msgBuilder.indexOf(" ") + 1);
                remindTime.append(msgBuilder.substring(0, msgBuilder.indexOf(" ")));

                // {remindTime} 有两种情况 分别是
                // a. {date}[空格]{time} ;
                // b. {time} ;
                // 提取 {remindTime}
                // 如果带有 . 或者 - 或者 年 说明 {remindTime} 带有 {date}
                if (remindTime.indexOf(".") > 0 || remindTime.indexOf("-") > 0 ||
                        remindTime.indexOf("年") > 0 || remindTime.indexOf("月") > 0) {
                    // 删除 {date}[空格]
                    msgBuilder.delete(0, msgBuilder.indexOf(" ") + 1);
                    remindTime.append(" ");
                    remindTime.append(msgBuilder.substring(0, msgBuilder.indexOf(" ")));
                }

                // 删除 {time}[空格]
                msgBuilder.delete(0, msgBuilder.indexOf(" ") + 1);

                // 提取 {remindMsg}
                remindMsg.append(msgBuilder);

                Remind thisRemind = new Remind();

                // 将时间根据时区格式化
                String strRemindTime = TimeKit.parseStringToFormalTimeFormat(remindTime.toString(), timeZone);
                // 若提醒模式非一次性提醒，需要对提醒时间进行处理，计算下一次的提醒的当地时间
                if (!mode.equals(onceFlag)) {
                    thisRemind.setRemindTime(strRemindTime);
                    thisRemind.setMode(mode);
//                    remindTime.replace(0, remindTime.length(), RemindHandler.nextRemind(thisRemind).getRemindTime());
                    remindTime.replace(0, remindTime.length(), nextRemind(thisRemind).getRemindTime());
                    strRemindTime = remindTime.toString();
                }

                // 若时区非北京时间则需要对提醒时间进去处理，将提醒时间转换到北京时间
                // 若提醒模式为 -w 系列，则修改 mode
                if (!timeZoneOption.equals(TimeZone_BJ)) {
                    // 设置时区
                    thisRemind.setTimezone(timeZone);

                    // 转换时间到北京时间
                    Date CNT = TimeKit.convertTimeTo(TimeKit.parseTime(strRemindTime), timeZone, prodTimeZone);
                    remindTime.replace(0, remindTime.length(), TimeKit.parseTime(CNT));

                    if (mode.startsWith(weekdayFlag)) {
                        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(prodTimeZone));
                        calendar.setTime(TimeKit.parseTime(remindTime.toString()));
                        int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                        if (weekday == 0) {
                            weekday = 7;
                        }
                        mode = "-w" + weekday;
                    }
                }

                remindTime.replace(0, remindTime.length(), TimeKit.parseStringToFormalTimeFormat(remindTime.toString(), timeZone));

                /*TODO*/
                // usable 选项尚未完成
                // 将提醒记录插入数据库中
                remindService.createRemind(
                        grpMsgHttpReqHandler.getGroupId(), // 提醒的群
                        remindMsg.toString(), // 提醒消息
                        remindTime.toString(), // 提醒时间
//                        TimeKit.parseStringToFormalTimeFormat(remindTime.toString(), timeZone), // 提醒时间
                        1, // 是否可用
                        mode,  // 提醒模式
                        TimeKit.getFormalTime(), // 提醒创建时间
                        grpMsgHttpReqHandler.getUserId() // 提醒创建者
                );

                // 设置回复

                response.setReply("设置提醒成功，将会在 " + timeZoneReply + " " +
                        TimeKit.parseTime(TimeKit.convertTimeTo(TimeKit.parseTime(remindTime.toString()), TimeZone_Default, timeZone)) +
                        " 进行提醒\n提醒消息为：" +
                        CQCodeKit.parseAtToFormalString(remindMsg.toString()));
                response.setFlag(true);

                /*TODO*/
                // 如果A恰好在提醒1要发生的时刻进行设置，那么会导致提醒1失效
                // 重启提醒
                restartCheckSchedule(remindService);

            } catch (TooManyOptionsException e) {
                response.setFlag(true);
                response.setReply(e.getMessage());
            } catch (InvalidRemindOptionException e) {
                response.setFlag(true);
                response.setReply(e.getMessage());
            }
        }
        return response;
    }

    /**
     * 查看提醒
     *
     * @param grpMsgHttpReqHandler
     * @return
     */
    @CQResponse
//    public static GroupMessageResponse getRemind(GrpMsgHttpReqHandler grpMsgHttpReqHandler) {
    public GroupMessageResponse getRemind(GrpMsgHttpReqHandler grpMsgHttpReqHandler) {
        GroupMessageResponse groupMessageResponse = new GroupMessageResponse();
        groupMessageResponse.setFlag(false);

        String msg = grpMsgHttpReqHandler.getMessage();
        String getRemindFlag = "查看提醒";
        if (msg.equals(getRemindFlag) && SenderKit.isAdminOrOwner(grpMsgHttpReqHandler)) {
            String guild = grpMsgHttpReqHandler.getGroupId();
            ArrayList<Remind> reminds = remindService.getRemindByGuild(guild);

            StringBuilder reply = new StringBuilder();
            for (Remind remind : reminds) {
                // 设置回复
                reply.append("id: ");
                reply.append(remind.getId());
                reply.append("  ");
                reply.append("下次提醒时间: ");
                reply.append(remind.getRemindTime());
                reply.append("  ");
                reply.append("提醒消息: ");
                reply.append(CQCodeKit.parseAtToFormalString(remind.getMessage()));
                reply.append("  ");
                reply.append("提醒模式: ");
                reply.append(remind.getMode());
                reply.append("\n");
            }
            groupMessageResponse.setReply(reply.toString().trim());
            groupMessageResponse.setFlag(true);
        }

        return groupMessageResponse;
    }

    /**
     * 删除提醒 可以批量删除
     *
     * @param grpMsgHttpReqHandler
     * @return
     */
    @CQResponse
//    public static GroupMessageResponse deleteRemind(GrpMsgHttpReqHandler grpMsgHttpReqHandler) {
    public GroupMessageResponse deleteRemind(GrpMsgHttpReqHandler grpMsgHttpReqHandler) {

        GroupMessageResponse groupMessageResponse = new GroupMessageResponse();
        groupMessageResponse.setFlag(false);

        String msg = grpMsgHttpReqHandler.getMessage();
        String guild = grpMsgHttpReqHandler.getGroupId();
        String deleteRemindFlag = "删除提醒";
        if (msg.startsWith(deleteRemindFlag) && SenderKit.isAdminOrOwner(grpMsgHttpReqHandler)) {
            StringBuilder deleteIds = new StringBuilder(msg.substring(msg.indexOf(" ") + 1).trim());
            while (deleteIds.indexOf("，") > 0) {
                deleteIds.replace(deleteIds.indexOf("，"), deleteIds.indexOf("，") + 1, ",");
            }
            while (deleteIds.indexOf(" ") > 0) {
                deleteIds.replace(deleteIds.indexOf(" "), deleteIds.indexOf(" ") + 1, ",");
            }
            String[] deleteId = deleteIds.toString().split(",");

            StringBuilder res = new StringBuilder();
            for (String id : deleteId) {
                Remind deleteRemind = remindService.getRemindById(id);
                if (guild.equals(deleteRemind.getGuild())) {
                    remindService.deleteRemind(id);
                    res.append(id);
                    res.append(" ");
                }
            }
            groupMessageResponse.setReply("删除提醒成功，删除的提醒 Id 为 " + res.toString().trim());
            groupMessageResponse.setFlag(true);
        }
        return groupMessageResponse;
    }
}
