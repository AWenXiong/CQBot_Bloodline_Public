package com.cq.httpapi.demo.dao.CQdao;

import com.cq.httpapi.demo.entity.Remind;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface RemindDao {

    @Select("select * " +
            "from remind")
    ArrayList<Remind> getAllRemindInfo();

    @Select("select guild, message, remind_time, mode " +
            "from remind " +
            "where usable = 1")
    ArrayList<Remind> getAllUsableRemind();

    @Select("select id, guild, message, remind_time, mode " +
            "from remind " +
            "where id = #{id} " +
            "and usable = 1")
    Remind getRemindInfoById(@Param("id") String id);

    @Select("select id, guild, message, remind_time, mode " +
            "from remind " +
            "where guild = #{guild} " +
            "and usable = 1")
    ArrayList<Remind> getRemindInfoByGuild(@Param("guild") String guild);

    @Select("select id, remind_time " +
            "from remind")
    Remind getRemindIdAndTime();

    @Select("select id, guild, message, remind_time, mode " +
            "from remind " +
            "where remind_time < date_add(now(), interval #{minute} minute) " +
            "and remind_time > now() " +
            "and usable = 1")
    ArrayList<Remind> getUsableRemindByTime(@Param("minute") Long minute);

    @Update("update remind " +
            "set guild = #{guild} " +
            "where id = #{id}")
    void updateRemindGuildById(@Param("id") String id,
                               @Param("guild") String guild);

    @Update("update remind " +
            "set message = #{message} " +
            "where id = #{id}")
    void updateRemindMessageById(@Param("id") String id,
                                 @Param("message") String message);

    @Update("update remind " +
            "set remind_time = #{remindTime} " +
            "where id = #{id}")
    void updateRemindTimeById(@Param("id") String id,
                              @Param("remindTime") String remindTime);

    @Update("update remind " +
            "set usable = #{usable} " +
            "where id = #{id}")
    void updateRemindUsableById(@Param("id") String id,
                                @Param("usable") int usable);

    @Update("update remind " +
            "set description = #{description} " +
            "where id = #{id}")
    void updateRemindDescriptionById(@Param("id") String id,
                                     @Param("description") String description);

    @Update("update remind " +
            "set create_user_id = #{userId} " +
            ", create_time = #{createTime}")
    void updateRemindCreatorInfoById(@Param("id") String id,
                                     @Param("createTime") Timestamp createTime,
                                     @Param("userId") String userId);

    @Update("update remind " +
            "set modified_user_id = #{userId} " +
            ", modified_time = #{modifiedTime}")
    void updateRemindUpdateInfoById(@Param("id") String id,
                                    @Param("modifiedTime") Timestamp modifiedTime,
                                    @Param("userId") String userId);

    @Insert("insert into remind(guild, message, remind_time, usable, mode, create_time, create_user_id, description) value" +
            "(#{guild}, #{message}, #{remindTime}, #{usable}, #{mode}, #{createTime}, #{createUserId}, '新建提醒')")
    void insertRemind(@Param("guild") String guild,
                      @Param("message") String message,
                      @Param("remindTime") String remindTime,
                      @Param("usable") int usable,
                      @Param("mode") String mode,
                      @Param("createTime") String createTime,
                      @Param("createUserId") String createUserId);

    @Delete("delete from remind " +
            "where id = #{id}")
    void deleteRemindById(@Param("id") String id);
}
