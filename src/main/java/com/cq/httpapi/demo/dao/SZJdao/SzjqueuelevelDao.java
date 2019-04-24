package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjqueuelevel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
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

    @Insert("insert into szjqueuelevel " +
            "value ()")
    void insertSzjitemscolor();

    @Update("update szjqueuelevel " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = {Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") Timestamp CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjqueuelevel " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") Timestamp ModifiedOn,
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

    @Select("select max(Id) from szjqueuelevel")
    long getMaxId();

    @Select("select last_insert_id() from szjqueuelevel")
    long getLastInsert();

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
