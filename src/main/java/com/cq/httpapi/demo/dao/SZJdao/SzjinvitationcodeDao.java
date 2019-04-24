package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjinvitationcode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface SzjinvitationcodeDao {

    @Select("select Id, Type, DueTime, EffectiveTime, InvitationCode, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjinvitationcode " +
            "where DeletionStateCode = 0")
    ArrayList<Szjinvitationcode> getAll();

    @Select("select Id, Type, DueTime, EffectiveTime, InvitationCode, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjinvitationcode " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjinvitationcode getById(@Param("Id") long Id);

    @Insert("insert into szjinvitationcode(Type, DueTime, EffectiveTime, InvitationCode, ExtraFrequency) " +
            "value(#{type}, #{dueTime}, #{effectiveTime}, #{invitationCode}, #{extraFrequency})")
    void createInvitationCode(@Param("type") String type,
                              @Param("dueTime") Timestamp dueTime,
                              @Param("effectiveTime") double effectiveTime,
                              @Param("invitationCode") String invitationCode,
                              @Param("extraFrequency") long extraFrequency);


    @Update("update szjinvitationcode " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = {Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") Timestamp CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjinvitationcode " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") Timestamp ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjinvitationcode " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjinvitationcode " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjinvitationcode " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) from szjinvitationcode")
    long getMaxId();

    @Select("select last_insert_id() from szjinvitationcode")
    long getLastInsert();

    @Update("update szjinvitationcode " +
            "set Type = #{Type} " +
            "where Id = #{Id}")
    void updateType(@Param("Id") long Id,
                    @Param("Type") String Type);

    @Update("update szjinvitationcode " +
            "set DueTime = #{DueTime} " +
            "where Id = #{Id}")
    void updateDueTime(@Param("Id") long Id,
                       @Param("DueTime") Timestamp DueTime);

    @Update("update szjinvitationcode " +
            "set EffectiveTime = #{EffectiveTime} " +
            "where Id = #{Id}")
    void updateEffectiveTime(@Param("Id") long Id,
                             @Param("EffectiveTime") long EffectiveTime);

    @Update("update szjinvitationcode " +
            "set InvitationCode = #{InvitationCode} " +
            "where Id = #{Id}")
    void updateInvitationCode(@Param("Id") long Id,
                              @Param("InvitationCode") String InvitationCode);
}
