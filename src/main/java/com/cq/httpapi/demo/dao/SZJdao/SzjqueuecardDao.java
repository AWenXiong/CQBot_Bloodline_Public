package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjqueuecard;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface SzjqueuecardDao {

    @Select("select Id, QueueInfoId, Level, CardInfoId, Position, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjqueuecard " +
            "where DeletionStateCode = 0")
    ArrayList<Szjqueuecard> getAll();

    @Select("select Id, QueueInfoId, Level, CardInfoId, Position, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjqueuecard " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjqueuecard getById(@Param("Id") long Id);

    @Select("select * " +
            "from szjqueuecard " +
            "where DeletionStateCode = 0 and " +
            "QueueInfoId = #{queueInfoId} and Level = #{level}")
    ArrayList<Szjqueuecard> getQueueInfoCard(@Param("queueInfoId") long queueInfoId,
                                             @Param("level") double level);

    @Insert("insert into szjqueuecard(QueueInfoId, Level, CardInfoId, Position) " +
            "value(#{queueInfoId}, #{level}, #{cardInfoId}, #{position})")
    void insertSzjqueuecard(@Param("queueInfoId") long queueInfoId,
                            @Param("level") double level,
                            @Param("cardInfoId") long cardInfoId,
                            @Param("position") double position);

    @Update("update szjqueuecard " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = #{Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") String CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjqueuecard " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") String ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjqueuecard " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjqueuecard " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjqueuecard " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) " +
            "from szjqueuecard " +
            "where DeletionStateCode = 0 " +
            "and QueueInfoId = #{queueInfoId}")
    long getLastInsertByQueueInfoId(@Param("queueInfoId") long queueInfoId);

    @Update("update szjqueuecard " +
            "set QueueInfoId = #{QueueInfoId} " +
            "where Id = #{Id}")
    void updateQueueInfoId(@Param("Id") long Id,
                           @Param("QueueInfoId") long QueueInfoId);

    @Update("update szjqueuecard " +
            "set Level = #{Level} " +
            "where Id = #{Id}")
    void updateLevel(@Param("Id") long Id,
                     @Param("Level") double Level);

    @Update("update szjqueuecard " +
            "set CardInfoId = #{CardInfoId} " +
            "where Id = #{Id}")
    void updateCardInfoId(@Param("Id") long Id,
                          @Param("CardInfoId") long CardInfoId);

    @Update("update szjqueuecard " +
            "set Position = #{Position} " +
            "where Id = #{Id}")
    void updatePosition(@Param("Id") long Id,
                        @Param("Position") double Position);
}
