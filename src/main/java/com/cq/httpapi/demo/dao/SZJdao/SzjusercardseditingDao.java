package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjusercardsediting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface SzjusercardseditingDao {

    @Select("select Id, UserId, GroupId, CardInfoId, " +
            "OriginLevel, OriginPosition, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjusercardsediting " +
            "where DeletionStateCode = 0")
    ArrayList<Szjusercardsediting> getAll();

    @Select("select Id, UserId, GroupId, CardInfoId, " +
            "OriginLevel, OriginPosition, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjusercardsediting " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjusercardsediting getById(@Param("Id") long Id);

    @Insert("insert into szjusercardsediting " +
            "value ()")
    void insertSzjitemscolor();

    @Update("update szjusercardsediting " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = {Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") Timestamp CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjusercardsediting " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") Timestamp ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjusercardsediting " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjusercardsediting " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjusercardsediting " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) from szjusercardsediting")
    long getMaxId();

    @Select("select last_insert_id() from szjusercardsediting")
    long getLastInsert();

    @Update("update szjusercardsediting " +
            "set UserId = #{UserId} " +
            "where Id = #{Id}")
    void updateUserId(@Param("Id") long Id,
                      @Param("UserId") long UserId);

    @Update("update szjusercardsediting " +
            "set GroupId = #{GroupId} " +
            "where Id = #{Id}")
    void updateGroupId(@Param("Id") long Id,
                       @Param("GroupId") long GroupId);

    @Update("update szjusercardsediting " +
            "set CardInfoId = #{CardInfoId} " +
            "where Id = #{Id}")
    void updateCardInfoId(@Param("Id") long Id,
                          @Param("CardInfoId") long CardInfoId);

    @Update("update szjusercardsediting " +
            "set OriginLevel = #{OriginLevel} " +
            "where Id = #{Id}")
    void updateOriginLevel(@Param("Id") long Id,
                           @Param("OriginLevel") int OriginLevel);

    @Update("update szjusercardsediting " +
            "set OriginPosition = #{OriginPosition} " +
            "where Id = #{Id}")
    void updateOriginPosition(@Param("Id") long Id,
                              @Param("OriginPosition") int OriginPosition);
}
