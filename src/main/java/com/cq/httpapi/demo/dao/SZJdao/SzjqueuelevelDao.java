package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjqueuelevel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface SzjqueuelevelDao {

    @Select("select Id, QueueInfoId, Level, UserSpellInfo, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjqueuelevel " +
            "where DeletionStateCode = 0")
    ArrayList<Szjqueuelevel> getAll();

    @Select("select QueueInfoId, Level, UserSpellInfo, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjqueuelevel " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjqueuelevel getById(@Param("Id") long Id);

    @Select("select * " +
            "from szjqueuelevel " +
            "where DeletionStateCode = 0 and " +
            "QueueInfoId = #{queueInfoId}")
    ArrayList<Szjqueuelevel> getQueueLevel(@Param("queueInfoId") long queueInfoId);

    @Insert("insert into szjqueuelevel(QueueInfoId, Level, UserSpellInfo) " +
            "value (#{queueInfoId}, #{level}, #{userSpellInfoId})")
    void insertSzjqueuelevel(@Param("queueInfoId") long queueInfoId,
                             @Param("level") double level,
                             @Param("userSpellInfoId") long userSpellInfoId);

    @Insert("insert into szjqueuelevel(QueueInfoId, Level) value" +
            "(#{queueInfoId}, #{level})")
    void insertSzjqueuelevelNullSpell(@Param("queueInfoId") long queueInfoId,
                                      @Param("level") double level);

    @Update("update szjqueuelevel " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = #{Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") String CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjqueuelevel " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") String ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjqueuelevel " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjqueuelevel " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjqueuelevel " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) " +
            "from szjqueuelevel " +
            "where DeletionStateCode = 0 " +
            "and QueueInfoId = #{queueInfoId}")
    long getLastInsert(@Param("queueInfoId") long queueInfoId);

    @Update("update szjqueuelevel " +
            "set QueueInfoId = #{QueueInfoId} " +
            "where Id = #{Id}")
    void updateQueueInfoId(@Param("Id") long Id,
                           @Param("QueueInfoId") long QueueInfoId);

    @Update("update szjqueuelevel " +
            "set Level = #{Level} " +
            "where Id = #{Id}")
    void updateLevel(@Param("Id") long Id,
                     @Param("Level") int Level);

    @Update("update szjqueuelevel " +
            "set UserSpellInfo = #{UserSpellInfo} " +
            "where Id = #{Id}")
    void updateUserSpellInfo(@Param("Id") long Id,
                             @Param("UserSpellInfo") long UserSpellInfo);
}
