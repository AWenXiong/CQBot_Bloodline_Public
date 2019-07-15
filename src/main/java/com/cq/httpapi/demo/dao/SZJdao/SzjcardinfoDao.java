package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjcardinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface SzjcardinfoDao {

    @Select("select * from szjcardinfo " +
            "where DeletionStateCode = 0")
    ArrayList<Szjcardinfo> getAllCards();

    @Insert("insert into szjcardinfo" +
            "(Id, Code, Name, NickName, Color, Color2, Sex, Occupation, Camp, " +
            "Description, DeletionStateCode, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy) value " +
            "(#{c.id}, #{c.code}, #{c.name}, #{c.nickName}, #{c.color}, #{c.color2}, #{c.sex}, #{c.occupation}, #{c.camp}, " +
            "#{c.description}, #{c.deletionStateCode}, #{c.sortCode}, " +
            "#{c.createOn}, #{c.createUserId}, #{c.createBy}, " +
            "#{c.modifiedOn}, #{c.modifiedUserId}, #{c.modifiedBy})")
    void insertCardInfo(@Param("c") Szjcardinfo szjcardinfo);
}
