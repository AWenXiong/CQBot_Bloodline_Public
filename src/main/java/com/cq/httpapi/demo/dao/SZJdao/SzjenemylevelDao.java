package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjenemylevel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface SzjenemylevelDao {

    @Select("select Id, EnemyInfoId, Level, FightingCapacity, " +
            "AdditionType, AdditionOption, AdditionAmmount, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjenemylevel " +
            "where DeletionStateCode = 0")
    ArrayList<Szjenemylevel> getAll();

    @Select("select Id, EnemyInfoId, Level, FightingCapacity, " +
            "AdditionType, AdditionOption, AdditionAmmount, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjenemylevel " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjenemylevel getById(@Param("Id") long Id);

    @Insert("insert into szjenemylevel " +
            "value ()")
    void insertSzjitemscolor();

    @Update("update szjenemylevel " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = {Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") Timestamp CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjenemylevel " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") Timestamp ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjenemylevel " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjenemylevel " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjenemylevel " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) from szjenemylevel")
    long getMaxId();

    @Select("select last_insert_id() from szjenemylevel")
    long getLastInsert();

    @Update("update szjenemylevel " +
            "set EnemyInfoId = #{EnemyInfoId} " +
            "where Id = #{Id}")
    void updateEnemyInfoId(@Param("Id") long Id,
                           @Param("EnemyInfoId") long EnemyInfoId);

    @Update("update szjenemylevel " +
            "set Level = #{Level} " +
            "where Id = #{Id}")
    void updateLevel(@Param("Id") long Id,
                     @Param("Level") int Level);

    @Update("update szjenemylevel " +
            "set FightingCapacity = #{FightingCapacity} " +
            "where Id = #{Id}")
    void updateFightingCapacity(@Param("Id") long Id,
                                @Param("FightingCapacity") long FightingCapacity);

    @Update("update szjenemylevel " +
            "set AdditionType = #{AdditionType} " +
            "where Id = #{Id}")
    void updateAdditionType(@Param("Id") long Id,
                            @Param("AdditionType") String AdditionType);

    @Update("update szjenemylevel " +
            "set AdditionOption = #{AdditionOption} " +
            "where Id = #{Id}")
    void updateAdditionOption(@Param("Id") long Id,
                              @Param("AdditionOption") String AdditionOption);

    @Update("update szjenemylevel " +
            "set AdditionAmmount = #{AdditionAmmount} " +
            "where Id = #{Id}")
    void updateAdditionAmmount(@Param("Id") long Id,
                               @Param("AdditionAmmount") int AdditionAmmount);
}

