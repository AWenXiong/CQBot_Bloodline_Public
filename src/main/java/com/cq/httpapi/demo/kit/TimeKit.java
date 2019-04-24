package com.cq.httpapi.demo.kit;

import com.cq.httpapi.demo.exception.GetNextDayException;
import com.cq.httpapi.demo.exception.GetNextWeekdayException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeKit {

    /**
     * 获取 yyyy-MM-dd HH:mm:ss 形式的当前时间
     *
     * @return
     */
    public static String getFormalTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = formatter.format(currentTime);
        return res;
    }

    /**
     * 获取 yyyy-MM-dd 形式的当前日期
     *
     * @return
     */
    public static String getFormalDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String res = formatter.format(currentTime);
        return res;
    }

    /**
     * 获取某个时区的 yyyy-MM-dd HH:mm:ss 形式的当前时间
     *
     * @param timeZone
     * @return
     */
    public static String getFormalTimeWithTimeZone(String timeZone) {
        TimeZone timeZone1 = TimeZone.getTimeZone(timeZone);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(timeZone1);
        String res = formatter.format(new Date());
        return res;
    }

    /**
     * 获取某个时区的 yyyy-MM-dd 形式的当前日期
     *
     * @param timeZone
     * @return
     */
    public static String getFormalDateWithTimeZone(String timeZone) {
        TimeZone timeZone1 = TimeZone.getTimeZone(timeZone);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(timeZone1);
        String res = formatter.format(new Date());
        return res;
    }

    /**
     * 将 Date 对象转换为 yyyy-MM-dd HH:mm:ss 形式的字符串
     *
     * @param date
     * @return
     */
    public static String parseTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = formatter.format(date);
        return res;
    }

    /**
     * 将 yyyy-MM-dd HH:mm:ss 形式的字符串转换为 Date
     *
     * @param time
     * @return
     */
    public static Date parseTime(String time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(time);
            return date;
        } catch (ParseException e) {
        }

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(time);
            return date;
        } catch (ParseException e) {
        }

        return null;
    }

    /**
     * 给定当地时间 time 以及 当地时区timezone 获取世界标准时
     *
     * @param localTime
     * @param timezone
     * @return
     */
    public static Date convertToGMT(Date localTime, String timezone) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));
        calendar.setTime(localTime);
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        calendar.add(Calendar.MILLISECOND, -(dstOffset + zoneOffset));
        return calendar.getTime();
    }

    /**
     * 给定 世界标准时GMT 获取 目标时区timezone 的当地时间
     *
     * @param GMT
     * @param timezone
     * @return
     */
    public static Date convertGMTTo(Date GMT, String timezone) {
        TimeZone timeZone = TimeZone.getTimeZone(timezone);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.setTime(GMT);
        calendar.setTimeZone(timeZone);
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        calendar.add(Calendar.MILLISECOND, dstOffset + zoneOffset);
        return calendar.getTime();
    }

    /**
     * 给定 当地时间localTime 以及 当地时区fromTimeZone
     * 转换到 目标时区toTimeZone 的时间
     *
     * @param localTime
     * @param fromTimeZone
     * @param toTimeZone
     * @return
     */
    public static Date convertTimeTo(Date localTime, String fromTimeZone, String toTimeZone) {
        Date GMT = convertToGMT(localTime, fromTimeZone);
        Date goalTime = convertGMTTo(GMT, toTimeZone);
        return goalTime;
    }

    /**
     * 支持4种转换
     * HH:mm:ss
     * yyyy-MM-dd HH:mm:ss
     * HH时mm分ss秒
     * yyyy年MM月dd日 HH时mm分ss秒
     * 转换成 yyyy-MM-dd HH:mm:ss
     *
     * @param msg
     * @return
     */
    public static String parseStringToFormalTimeFormat(String msg, String timezone) {

        if (msg.contains("时")) {
            String newMsg = msg.replace("时", ":");
            return parseStringToFormalTimeFormat(newMsg, timezone);
        } else if (msg.contains("点")) {
            String newMsg = msg.replace("点", ":");
            return parseStringToFormalTimeFormat(newMsg, timezone);
        } else if (msg.contains("分")) {
            String newMsg = msg.replace("分", ":");
            return parseStringToFormalTimeFormat(newMsg, timezone);
        } else if (msg.contains("秒")) {
            String newMsg = msg.replace("秒", "");
            return parseStringToFormalTimeFormat(newMsg, timezone);
        } else if (msg.contains("日")) {
            String newMsg = msg.replace("日", "");
            return parseStringToFormalTimeFormat(newMsg, timezone);
        } else if (msg.contains("年") || msg.contains("月")) {
            String newMsg = parseCNDate(msg);
            return parseStringToFormalTimeFormat(newMsg, timezone);
        } else if (msg.contains(".")) { // .用于日期
            String newMsg = msg.replace(".", "-");
            return parseStringToFormalTimeFormat(newMsg, timezone);
        } else if (msg.contains("：")) { // 中文冒号 用于时间
            String newMsg = msg.replace("：", ":");
            return parseStringToFormalTimeFormat(newMsg, timezone);
        } else if (msg.contains("-")) { // 说明时间格式为 yyyy-MM-dd HH:mm:ss
            String time = msg.trim();
            if (time.endsWith(":")) { // ??
                time += "0";
            }
            Date dateTime = parseTime(time);
            return parseTime(dateTime);
        } else if (msg.contains(":")) {
            String time = getFormalDateWithTimeZone(timezone) + " " + msg.trim();
            if (time.endsWith(":")) { // ??
                time += "0";
            }
            Date dateTime = parseTime(time);
            return parseTime(dateTime);
        }
        return "ERROR!";
    }


    /**
     * 将 yyyy年MM月dd日 HH时mm分ss秒 形式的日期转化为 yyyy-MM-dd HH:mm:ss
     *
     * @param msg
     * @return
     */
    public static String parseCNDate(String msg) {

        StringBuilder res = new StringBuilder();

        if (msg.contains("年")) {
            res.append(msg);
            int yearIndex = msg.indexOf("年");
            res.replace(yearIndex, yearIndex + 1, "-");
        } else if (msg.contains("月")) {
            res.append(getFormalDate());
            res.replace(res.indexOf("-") + 1, res.length(), msg);
        }

        if (msg.contains("月")) {
            int monthIndex = res.indexOf("月");
            res.replace(monthIndex, monthIndex + 1, "-");
        }

        System.err.println(res);
        return res.toString();
    }

    /**
     * 获取下一个星期几
     * weekday 为星期X 取值范围为 1..7
     * 1代表星期一
     * 7代表星期日
     *
     * @param weekday
     * @return
     * @throws GetNextWeekdayException
     */
    public static Date getNextWeekDayWithTimeZone(Date fromDate, int weekday, String timezone) {
        if (weekday > 7) {
            throw new GetNextWeekdayException("Parma weekday greater than 7");
        } else if (weekday < 1) {
            throw new GetNextWeekdayException("Parma weekday less than 1");
        }

        weekday += 1;
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));
        calendar.setTime(fromDate);
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        if (today > weekday) {
            calendar.add(Calendar.DATE, 7 - today + weekday);
        } else if (today < weekday) {
            calendar.add(Calendar.DATE, weekday - today);
        } else if (today == weekday) {
            calendar.add(Calendar.DATE, 7);
        }
        Date res = new Date(calendar.getTimeInMillis());
        return res;
    }

    public static Date getNextWeekDay(String fromDate, int weekday) throws GetNextWeekdayException {
        return getNextWeekDayWithTimeZone(TimeKit.parseTime(fromDate), weekday, "GMT+8");
    }

    public static Date getNextWeekday(int weekday) throws GetNextWeekdayException {
        return getNextWeekDay((new Date()).toString(), weekday);
    }

    /**
     * 获取间隔 interval天 后的日期
     * 2019-1-1 两天后是 2019-1-3
     *
     * @param interval
     * @return
     * @throws GetNextDayException
     */
    public static Date getDateWithTimeZone(Date fromDate, int interval, String timezone) {
        if (interval < 0) {
            throw new GetNextDayException("Param interval less than 0");
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));
        calendar.setTime(fromDate);
        calendar.add(Calendar.DATE, interval);
        Date res = new Date(calendar.getTimeInMillis());
        return res;
    }

    public static Date getDate(String fromDate, int interval) throws GetNextWeekdayException {
        return getDateWithTimeZone(TimeKit.parseTime(fromDate), interval, "GMT+8");
    }

    public static Date getDate(int interval) throws GetNextDayException {
        return TimeKit.getDate(TimeKit.getFormalDate(), interval);
    }

}
