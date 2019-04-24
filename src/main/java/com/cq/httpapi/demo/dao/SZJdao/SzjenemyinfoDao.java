package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjenemyinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface SzjenemyinfoDao {

    @Select("select Id, Code, Name, Enabled, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjenemyinfo " +
            "where DeletionStateCode = 0")
    ArrayList<Szjenemyinfo> getAll();

    @Select("select Id, Code, Name, Enabled, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjenemyinfo " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjenemyinfo getById(@Param("Id") long Id);

    @Insert("insert into szjenemyinfo " +
            "value ()")
    void insertSzjitemscolor();

    @Update("update szjenemyinfo " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = {Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") Timestamp CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjenemyinfo " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") Timestamp ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjenemyinfo " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjenemyinfo " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjenemyinfo " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) from szjenemyinfo")
    long getMaxId();

    @Select("select last_insert_id() from szjenemyinfo")
    long getLastInsert();

    @Update("update szjenemyinfo " +
            "set Code = #{Code} " +
            "where Id = #{Id}")
    void updateCode(@Param("Id") long Id,
                    @Param("Code") long Code);

    @Update("update szjenemyinfo " +
            "set Name = #{Name} " +
            "where Id = #{Id}")
    void updateName(@Param("Id") long Id,
                    @Param("Name") String Name);

    @Update("update szjenemyinfo " +
            "set Enabled = #{Enabled }" +
            "where Id = #{Id}")
    void updateEnabled(@Param("Id") long Id,
                       @Param("Enabled") int Enabled);

}
