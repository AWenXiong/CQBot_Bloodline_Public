package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjitemscolor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface SzjitemscolorDao {

    @Select("select Id, ItemCode, ItemName, ItemValue, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjitemscolor " +
            "where DeletionStateCode = 0")
    ArrayList<Szjitemscolor> getAll();

    @Select("select Id, ItemCode, ItemName, ItemValue, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjitemscolor " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjitemscolor getById(@Param("Id") long Id);

    @Insert("insert into szjitemscolor(ItemCode, ItemName, ItemValue, DeletionStateCode) " +
            "value (#{ItemCode}, #{ItemName}, #{ItemValue}, 0)")
    void insertSzjitemscolor(@Param("ItemCode") String ItemCode,
                             @Param("ItemName") String ItemName,
                             @Param("ItemValue") String ItemValue);

    @Update("update szjitemscolor " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CretateuserId}, CreateBy = #{CreateBy} " +
            "where Id = #{Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") Timestamp CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjitemscolor " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") Timestamp ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjitemscolor " +
            "set ItemCode = #{ItemCode} " +
            "where Id = #{Id}")
    void updateItemCode(@Param("Id") long Id,
                        @Param("ItemCode") String ItemCode);

    @Update("update szjitemscolor " +
            "set ItemName = #{ItemName} " +
            "where Id = #{Id}")
    void updateItemName(@Param("Id") long Id,
                        @Param("ItemName") String ItemName);

    @Update("update szjitemscolor " +
            "set ItemValue = #{ItemValue} " +
            "where Id = #{Id}")
    void updateItemValue(@Param("Id") long Id,
                         @Param("ItemValue") String ItemValue);

    @Update("update szjitemscolor " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjitemscolor " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjitemscolor " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) from szjitemscolor")
    long getMaxId();

    @Select("select last_insert_id() from szjitemscolor")
    long getLastInsert();
}
