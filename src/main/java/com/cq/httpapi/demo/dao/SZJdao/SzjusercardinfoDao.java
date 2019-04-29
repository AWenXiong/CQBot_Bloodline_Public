package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjusercardinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface SzjusercardinfoDao {

    @Select("select Id, CardInfoId, UserId, GroupId, " +
            "FightingCapacity, Fate, IsGodofWar, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjusercardinfo " +
            "where DeletionStateCode = 0")
    ArrayList<Szjusercardinfo> getAll();

    @Select("select Id, CardInfoId, UserId, GroupId, " +
            "FightingCapacity, Fate, IsGodofWar, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjusercardinfo " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjusercardinfo getById(@Param("Id") long Id);

    @Select("select * " +
            "from szjusercardinfo " +
            "where DeletionStateCode = 0 and " +
            "UserId = #{userId} and GroupId = #{groupId}")
    ArrayList<Szjusercardinfo> getByUserId(@Param("userId") long userId,
                                           @Param("groupId") long groupId);

    @Insert("insert into szjusercardinfo(CardInfoId, UserId, GroupId, FightingCapacity, Fate, IsGodofWar) " +
            "value (#{cardInfoId}, #{userId}, #{groupId}, #{fightingCapacity}, #{fate}, #{isGodofWar})")
    void insertSzjusercardinfo(@Param("cardInfoId") long cardInfoId,
                               @Param("userId") long userId,
                               @Param("groupId") long groupId,
                               @Param("fightingCapacity") long fightingCapacity,
                               @Param("fate") long fate,
                               @Param("isGodofWar") long isGodofWar);

    @Update("update szjusercardinfo " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = {Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") String CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjusercardinfo " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") String ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjusercardinfo " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjusercardinfo " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjusercardinfo " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) " +
            "from szjusercardinfo " +
            "where DeletionStateCode = 0 and GroupId = #{groupId}")
    long getLastInsert(@Param("groupId") long groupId);

    @Update("update szjusercardinfo " +
            "set CardInfoId = #{CardInfoId} " +
            "where Id = #{Id}")
    void updateCardInfoId(@Param("Id") long Id,
                          @Param("CardInfoId") long CardInfoId);

    @Update("update szjusercardinfo " +
            "set UserId = #{UserId} " +
            "where Id = #{Id}")
    void updateUserId(@Param("Id") long Id,
                      @Param("UserId") long UserId);

    @Update("update szjusercardinfo " +
            "set GroupId = #{GroupId} " +
            "where Id = #{Id}")
    void updateGroupId(@Param("Id") long Id,
                       @Param("GroupId") long GroupId);

    @Update("update szjusercardinfo " +
            "set FightingCapacity = #{FightingCapacity} " +
            "where Id = #{Id}")
    void updateFightingCapacity(@Param("Id") long Id,
                                @Param("FightingCapacity") long FightingCapacity);

    @Update("update szjusercardinfo " +
            "set Fate = #{Fate} " +
            "where Id = #{Id}")
    void updateFate(@Param("Id") long Id,
                    @Param("Fate") long Fate);

    @Update("update szjusercardinfo " +
            "set GodofWar = #{GodOfWar} " +
            "where Id = #{Id}")
    void updateIsGodOfWar(@Param("Id") long Id,
                          @Param("GodOfWar") long GodOfWar);
}
