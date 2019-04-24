package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjitemssex;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface SzjitemssexDao {

    @Select("select Id, ItemCode, ItemName, ItemValue, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjitemssex " +
            "where DeletionStateCode = 0")
    ArrayList<Szjitemssex> getAll();

    @Select("select Id, ItemCode, ItemName, ItemValue, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjitemssex " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjitemssex getById(@Param("Id") long Id);

    @Insert("insert into szjitemssex(ItemCode, ItemName, ItemValue) " +
            "value (#{ItemCode}, #{ItemName}, #{ItemValue})")
    void insertSzjitemssex(@Param("ItemCode") String ItemCode,
                           @Param("ItemName") String ItemName,
                           @Param("ItemValue") String ItemValue);

    @Update("update szjitemssex " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CretateuserId}, CreateBy = #{CreateBy} " +
            "where Id = {Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") Timestamp CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjitemssex " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") Timestamp ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjitemssex " +
            "set ItemCode = #{ItemCode} " +
            "where Id = #{Id}")
    void updateItemCode(@Param("Id") long Id,
                        @Param("ItemCode") String ItemCode);

    @Update("update szjitemssex " +
            "set ItemName = #{ItemName} " +
            "where Id = #{Id}")
    void updateItemName(@Param("Id") long Id,
                        @Param("ItemName") String ItemName);

    @Update("update szjitemssex " +
            "set ItemValue = #{ItemValue} " +
            "where Id = #{Id}")
    void updateItemValue(@Param("Id") long Id,
                         @Param("ItemValue") String ItemValue);

    @Update("update szjitemssex " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjitemssex " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjitemssex " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) from szjitemssex")
    long getMaxId();

    @Select("select last_insert_id()")
    long getLastInsert();

}
