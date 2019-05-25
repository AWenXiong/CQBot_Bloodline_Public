package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjcardinfoext;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface SzjcardinfoextDao {

    @Select("select Id, CardInfoId, ParameterCode, ParameterValue, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjcardinfoext " +
            "where DeletionStateCode = 0")
    ArrayList<Szjcardinfoext> getAll();

    @Select("select Id, CardInfoId, ParameterCode, ParameterValue, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjcardinfoext " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjcardinfoext getById(@Param("Id") long Id);

    @Insert("insert into szjcardinfoext " +
            "value ()")
    void insertSzjitemscolor();

    @Update("update szjcardinfoext " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = #{Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") Timestamp CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjcardinfoext " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") Timestamp ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjcardinfoext " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjcardinfoext " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjcardinfoext " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) from szjcardinfoext")
    long getMaxId();

    @Select("select last_insert_id() from szjcardinfoext")
    long getLastInsert();

    @Update("update szjcardinfoext " +
            "set CardInfoId = #{CardInfoId} " +
            "where Id = #{Id}")
    void updateCardinfoId(@Param("Id") long Id,
                          @Param("CardInfoId") long CardInfoId);

    @Update("update szjcardinfoext " +
            "set ParameterCode = #{ParameterCode} " +
            "where Id = #{Id}")
    void updateParameterCode(@Param("Id") long Id,
                             @Param("ParameterCode") String ParameterCode);

    @Update("update szjcardinfoext " +
            "set ParameterValue = #{ParameterValue} " +
            "where Id = #{Id}")
    void updateParameterValue(@Param("Id") long Id,
                              @Param("ParameterValue") String ParameterValue);
}
