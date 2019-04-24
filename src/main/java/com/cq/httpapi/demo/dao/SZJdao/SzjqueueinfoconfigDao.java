package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjqueueinfoconfig;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface SzjqueueinfoconfigDao {

    @Select("select Id, UserId, GroupId, ParameterCode, ParameterValue, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjqueueinfoconfig " +
            "where DeletionStateCode = 0")
    ArrayList<Szjqueueinfoconfig> getAll();

    @Select("select UserId, GroupId, ParameterCode, ParameterValue, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjqueueinfoconfig " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjqueueinfoconfig getById(@Param("Id") long Id);

    @Insert("insert into szjqueueinfoconfig " +
            "value ()")
    void insertSzjitemscolor();

    @Update("update szjqueueinfoconfig " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = {Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") Timestamp CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjqueueinfoconfig " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") Timestamp ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjqueueinfoconfig " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjqueueinfoconfig " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjqueueinfoconfig " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) from szjqueueinfoconfig")
    long getMaxId();

    @Select("select last_insert_id() from szjqueueinfoconfig")
    long getLastInsert();

    @Update("update szjqueueinfoconfig " +
            "set GroupId = #{UserId} " +
            "where Id = #{Id}")
    void updateUserId(@Param("Id") long Id,
                      @Param("UserId") long UserId);

    @Update("update szjqueueinfoconfig " +
            "set GroupId = {GroupId} " +
            "where Id = #{Id}")
    void updateGroupId(@Param("Id") long Id,
                       @Param("GroupId") long GroupId);

    @Update("update szjqueueinfoconfig " +
            "set ParameterCode = #{ParameterCode} " +
            "where Id = #{Id}")
    void updateParameterCode(@Param("Id") long Id,
                             @Param("ParameterCode") String ParameterCode);

    @Update("update szjqueueinfoconfig " +
            "set ParameterValue = #{ParameterValue} " +
            "where Id = #{Id}")
    void updateParameterValue(@Param("Id") long Id,
                              @Param("ParameterValue") String ParameterValue);
}
