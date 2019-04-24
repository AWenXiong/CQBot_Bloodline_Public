package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjspellinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface SzjspellinfoDao {
    @Select("select Id, Code, Name, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjspellinfo " +
            "where DeletionStateCode = 0")
    ArrayList<Szjspellinfo> getAll();

    @Select("select Id, Code, Name, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjspellinfo " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjspellinfo getById(@Param("Id") long Id);

    @Insert("insert into szjspellinfo " +
            "value ()")
    void insertSzjitemscolor();

    @Update("update szjspellinfo " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = {Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") Timestamp CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjspellinfo " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") Timestamp ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjspellinfo " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjspellinfo " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjspellinfo " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) from szjspellinfo")
    long getMaxId();

    @Select("select last_insert_id() from szjspellinfo")
    long getLastInsert();

    @Update("update szjspellinfo " +
            "set Code = #{Code} " +
            "where Id = #{Id}")
    void updateCode(@Param("Id") long Id,
                    @Param("Code") String Code);

    @Update("update szjspellinfo " +
            "set Code = #{Name} " +
            "where Id = #{Id}")
    void updateName(@Param("Id") long Id,
                    @Param("Name") String Name);
}
