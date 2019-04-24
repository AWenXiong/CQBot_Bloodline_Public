package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjuserspellinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface SzjuserspellinfoDao {

    @Select("select Id, UserId, GroupId, SpellId, FightingCapacity, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjuserspellinfo " +
            "where DeletionStateCode = 0")
    ArrayList<Szjuserspellinfo> getAll();

    @Select("select Id, UserId, GroupId, SpellId, FightingCapacity, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjuserspellinfo " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjuserspellinfo getById(@Param("Id") long Id);

    @Insert("insert into szjuserspellinfo " +
            "value ()")
    void insertSzjitemscolor();

    @Update("update szjuserspellinfo " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = {Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") Timestamp CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjuserspellinfo " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") Timestamp ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjuserspellinfo " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjuserspellinfo " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjuserspellinfo " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) from szjuserspellinfo")
    long getMaxId();

    @Select("select last_insert_id() from szjuserspellinfo")
    long getLastInsert();

    @Update("update szjuserspellinfo " +
            "set " +
            "where Id = #{Id}")
    void updateUserId();

    @Update("update szjuserspellinfo " +
            "set GroupId = #{GroupId} " +
            "where Id = #{Id}")
    void updateGroupId(@Param("Id") long Id,
                       @Param("GroupId") long GroupId);

    @Update("update szjuserspellinfo " +
            "set SpellId = #{SpellId} " +
            "where Id = #{Id}")
    void updateSpellId(@Param("Id") long Id,
                       @Param("SpellId") long SpellId);

    @Update("update szjuserspellinfo " +
            "set FightingCapacity = #{FightingCapacity} " +
            "where Id = #{Id}")
    void updateFightingCapacity(@Param("Id") long Id,
                                @Param("FightingCapacity") long FightingCapacity);
}
