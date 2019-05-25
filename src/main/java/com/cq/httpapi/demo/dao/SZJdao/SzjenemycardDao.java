package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjenemycard;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface SzjenemycardDao {

    @Select("select Id, EnemyInfoId, Level, CardInfoId, IsLeader, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjenemycard " +
            "where DeletionStateCode = 0")
    ArrayList<Szjenemycard> getAll();

    @Select("select Id, EnemyInfoId, Level, CardInfoId, IsLeader, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjenemycard " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjenemycard getById(@Param("Id") long Id);

    @Select("select * " +
            "from szjenemycard " +
            "where DeletionStateCode = 0 and " +
            "EnemyInfoId = #{enemyInfoId} and Level = #{enemyLevel}")
    ArrayList<Szjenemycard> getByGroupAndLevel(@Param("enemyInfoId") long enemyInfoId,
                                               @Param("enemyLevel") long level);

    @Select("select * " +
            "from szjenemycard " +
            "where DeletionStateCode = 0 and " +
            "EnemyInfoId = #{enemyInfoId}")
    ArrayList<Szjenemycard> getByGroup(@Param("enemyInfoId") long enemyInfoId);

    @Insert("insert into szjenemycard " +
            "value ()")
    void insertSzjitemscolor();

    @Update("update szjenemycard " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = #{Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") Timestamp CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjenemycard " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") Timestamp ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjenemycard " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjenemycard " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjenemycard " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) from szjenemycard")
    long getMaxId();

    @Select("select last_insert_id() from szjenemycard")
    long getLastInsert();

    @Update("update szjenemycard " +
            "set EnemyInfoId = #{EnemyInfoId} " +
            "where Id = #{Id}")
    void updateEnemyInfoId(@Param("Id") long Id,
                           @Param("EnemyInfoId") long EnemyInfoId);

    @Update("update szjenemycard " +
            "set Level = #{Level} " +
            "where Id = #{Id}")
    void updateLevel(@Param("Id") long Id,
                     @Param("Level") int Level);

    @Update("update szjenemycard " +
            "set CardInfoId = #{CardInfoId} " +
            "where Id = #{Id}")
    void updateCardInfoId(@Param("Id") long Id,
                          @Param("CardInfoId") long CardInfoId);

    @Update("update szjenemycard " +
            "set IsLeader = #{IsLeader} " +
            "where Id = #{Id}")
    void updateIsLeader(@Param("Id") long Id,
                        @Param("IsLeader") int IsLeader);
}
