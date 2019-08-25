package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface SzjuserinfoDao {

    @Select("select Id, Code, Name, Password, " +
            "Openid, WechatOpenid, Mobile, Email, FreeQueueTime, Type" +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjuserinfo " +
            "where DeletionStateCode = 0")
    ArrayList<Szjuserinfo> getAll();

    @Select("select Id, Code, Name, Password, " +
            "Openid, WechatOpenid, Mobile, Email, FreeQueueTime, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjuserinfo " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjuserinfo getById(@Param("Id") Long Id);

    @Select("select Id, Code, Name, Openid, Mobile, Email, FreeQueueTime, WechatOpenid " +
            "from szjuserinfo " +
            "where code = #{code} and password = #{password}")
    Szjuserinfo login(@Param("code") String code,
                      @Param("password") String password);

    @Select("select Id from szjuserinfo " +
            "where Code = #{userName}")
    Long getIdByCode(@Param("userName") String userName);

    @Select("select count(*) from szjuserinfo " +
            "where Code = #{code}")
    Long existCode(@Param("code") String userName);


    @Select("select count(*) " +
            "from szjuserinfo " +
            "where Openid = #{openId}")
    Long existOpenId(@Param("openId") String openId);

    @Select("select count(*) " +
            "from szjuserinfo " +
            "where WechatOpenid = #{wechatOpenId}")
    Long existWechatOpenId(@Param("wechatOpenId") String WechatOpenId);

    @Select("select Id, Code, Name, Openid, Mobile, Email, FreeQueueTime, WechatOpenid " +
            "from szjuserinfo " +
            "where Openid = #{openId}")
    Szjuserinfo getByOpenId(@Param("openId") String openId);

    @Insert("insert into szjuserinfo(Name, Password, Code, Openid, FreeQueueTime, DeletionStateCode) " +
            "value (#{userName}, #{password}, #{code}, #{openId}, 0, 0)")
    void insertSzjuserinfo(@Param("code") String code,
                           @Param("password") String password,
                           @Param("userName") String userName,
                           @Param("openId") String openId);


    @Update("update szjuserinfo " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = #{Id}")
    void updateCreateInfo(@Param("Id") Long Id,
                          @Param("CreateOn") String CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjuserinfo " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") Long Id,
                          @Param("ModifiedOn") String ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjuserinfo " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") Long Id,
                           @Param("Desc") String Description);

    @Update("update szjuserinfo " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") Long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjuserinfo " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") Long Id);

    @Select("select max(Id) from szjuserinfo")
    long getMaxId();

    @Select("select last_insert_id() from szjuserinfo")
    long getLastInsert();

    @Update("update szjuserinfo " +
            "set Code = #{Code} " +
            "where Id = #{Id}")
    void updateCode(@Param("Id") Long Id,
                    @Param("Code") String Code);

    @Update("update szjuserinfo " +
            "set Name = #{Name} " +
            "where Id = #{Id}")
    void updateName(@Param("Id") Long Id,
                    @Param("Name") String Name);

    @Update("update szjuserinfo " +
            "set Password = #{Password} " +
            "where Id = #{Id}")
    void updatePassword(@Param("Id") Long Id,
                        @Param("Password") String Password);

    @Update("update szjuserinfo " +
            "set Openid = #{Openid} " +
            "where Id = #{Id}")
    void updateOpenid(@Param("Id") Long Id,
                      @Param("Openid") String Openid);

    @Update("update szjuserinfo " +
            "set WechatOpenid = #{WechatOpenid} " +
            "where Id = #{Id}")
    void updateWechatOpenid(@Param("Id") Long Id,
                            @Param("WechatOpenid") String WechatOpenid);

    @Update("update szjuserinfo " +
            "set Mobile = #{Mobile} " +
            "where Id = #{Id}")
    void updateMobile(@Param("Id") Long Id,
                      @Param("Mobile") String Mobile);

    @Update("update szjuserinfo " +
            "set Email = #{Email} " +
            "where Id = #{Id}")
    void updateEmail(@Param("Id") Long Id,
                     @Param("Email") String Email);

    @Update("update szjuserinfo " +
            "set FreeQueueTime = #{FreeQueueTime} " +
            "where Id = #{Id}")
    void updateFreeQueueTime(@Param("Id") Long Id,
                             @Param("FreeQueueTime") int FreeQueueTime);

    @Update("update szjuserinfo " +
            "set Type = #{Type} " +
            "where Id = #{Id}")
    void updateType(@Param("Id") Long Id,
                    @Param("Type") String Type);
}
