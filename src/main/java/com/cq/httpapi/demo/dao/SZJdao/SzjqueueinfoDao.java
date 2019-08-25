package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjqueueinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface SzjqueueinfoDao {

    @Select("select Id, UserId, GroupId, Enabled, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjqueueinfo " +
            "where DeletionStateCode = 0")
    ArrayList<Szjqueueinfo> getAll();

    @Select("select Id, UserId, GroupId, Enabled, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjqueueinfo " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjqueueinfo getById(@Param("Id") long Id);

    @Select("select * " +
            "from szjqueueinfo " +
            "where DeletionStateCode = 0 and " +
            "UserId = #{userId} and groupId = #{groupId} and Enabled = 1")
    Szjqueueinfo getQueueInfo(@Param("userId") long userId,
                              @Param("groupId") long groupId);

    ;

    @Insert("insert into szjqueueinfo(UserId, GroupId, Enabled) " +
            "value (#{userId}, #{groupId}, 1)")
    void insertSzjqueueinfo(@Param("userId") long userId,
                            @Param("groupId") long groupId);

    @Update("update szjqueueinfo " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = #{Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") String CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjqueueinfo " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") String ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjqueueinfo " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjqueueinfo " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjqueueinfo " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) " +
            "from szjqueueinfo " +
            "where GroupId = #{groupId}")
    long getLastInsert(@Param("groupId") long groupId);

    @Update("update szjqueueinfo " +
            "set UserId = #{UserId} " +
            "where Id = #{Id}")
    void updateUserId(@Param("Id") long Id,
                      @Param("UserId") long UserId);

    @Update("update szjqueueinfo " +
            "set GroupId = #{GroupId} " +
            "where Id = #{Id}")
    void updateGroupId(@Param("Id") long Id,
                       @Param("GroupId") long GroupId);

    @Update("update szjqueueinfo " +
            "set Enabled = #{Enabled} " +
            "where Id = #{Id}")
    void updateEnabled(@Param("Id") long Id,
                       @Param("Enabled") long Enabled);

    @Update("update szjqueueinfo " +
            "set Enabled = 0 " +
            "where GroupId = #{groupId}")
    void updateEnabledByGroupId(@Param("groupId") long groupId);
}
