package com.cq.httpapi.demo.dao.CQdao;

import com.cq.httpapi.demo.entity.CQ.BloodlineItem;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface BloodlineItemDao {

    @Select("select id, name, type, description " +
            "from bloodline_item " +
            "where name = #{name}")
    BloodlineItem getByName(@Param("name") String name);

    @Select("select id, name, type, description " +
            "from bloodline_item " +
            "where id = #{id}")
    BloodlineItem getById(@Param("id") long id);

    @Select("select id, name, type, description " +
            "from bloodline_item " +
            "where type = #{type}")
    ArrayList<BloodlineItem> getByType(@Param("type") String type);


    @Select("select count(*) " +
            "from bloodline_item " +
            "where name = #{name}")
    long existItemName(@Param("name") String itemName);


    @Insert("insert into bloodline_item(name, type, description) value " +
            "(#{name}, #{type}, #{description})")
    void createItem(@Param("name") String name,
                    @Param("type") String type,
                    @Param("description") String description);

    @Delete("delete from bloodline_item " +
            "where id = #{id}")
    void deleteById(@Param("id") long id);

    @Delete("delete from bloodline_item " +
            "where name = #{name}")
    void deleteByName(@Param("name") String name);

    @Update("update bloodline_item " +
            "set name = #{name} " +
            "where id = #{id}")
    void updateName(@Param("id") long id,
                    @Param("name") String name);

    @Update("update bloodline_item " +
            "set type = #{type} " +
            "where id = #{id}")
    void updateType(@Param("id") long id,
                    @Param("type") String type);

    @Update("update bloodline_item " +
            "set description = #{desc} " +
            "where id = #{id}")
    void updateDescription(@Param("id") long id,
                           @Param("desc") String description);
}
