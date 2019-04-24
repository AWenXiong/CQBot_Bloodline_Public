package com.cq.httpapi.demo.dao.SZJdao;

import com.cq.httpapi.demo.entity.SZJ.Szjusercardgroupinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface SzjusercardgroupinfoDao {
    @Select("select Id, UserId, Name, InvitationCode, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjusercardgroupinfo " +
            "where DeletionStateCode = 0")
    ArrayList<Szjusercardgroupinfo> getAll();

    @Select("select Id, UserId, Name, InvitationCode, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjusercardgroupinfo " +
            "where DeletionStateCode = 0 and " +
            "Id = #{Id}")
    Szjusercardgroupinfo getById(@Param("Id") long Id);

    @Select("select Id, UserId, Name, InvitationCode, " +
            "Description, SortCode, " +
            "CreateOn, CreateUserId, CreateBy, " +
            "ModifiedOn, ModifiedUserId, ModifiedBy " +
            "from szjusercardgroupinfo " +
            "where DeletionStateCode = 0 and " +
            "UserId = #{userId}")
    ArrayList<Szjusercardgroupinfo> getByUserId(@Param("userId") long userId);

    @Insert("insert into szjusercardgroupinfo(UserId, Name, InvitationCode) " +
            "value (#{UserId}, #{Name}, #{InvitationCode})")
    void insertSZJUserCardGroupInfo(@Param("UserId") long userId,
                                    @Param("Name") String name,
                                    @Param("InvitationCode") String invitationCode);

    @Update("update szjusercardgroupinfo " +
            "set CreateOn = #{CreateOn}, CreateUserId = #{CreateUserId}, CreateBy = #{CreateBy} " +
            "where Id = #{Id}")
    void updateCreateInfo(@Param("Id") long Id,
                          @Param("CreateOn") String CreateOn,
                          @Param("CreateUserId") String CreateUserId,
                          @Param("CreateBy") String CreateBy);

    @Update("update szjusercardgroupinfo " +
            "set ModifiedOn = #{ModifiedOn}, ModifiedUserId = #{ModifiedUserId}, ModifiedBy = #{ModifiedBy} " +
            "where Id = #{Id}")
    void updateModifyInfo(@Param("Id") long Id,
                          @Param("ModifiedOn") String ModifiedOn,
                          @Param("ModifiedUserId") String ModifiedUserId,
                          @Param("ModifiedBy") String ModifiedBy);

    @Update("update szjusercardgroupinfo " +
            "set Description = #{Desc} " +
            "where Id = #{Id}")
    void updateDescription(@Param("Id") long Id,
                           @Param("Desc") String Description);

    @Update("update szjusercardgroupinfo " +
            "set SortCode = #{SortCode} " +
            "where Id = #{Id}")
    void updateSortCode(@Param("Id") long Id,
                        @Param("SortCode") int SortCode);

    @Update("update szjusercardgroupinfo " +
            "set DeletionStateCode = 1 " +
            "where Id = #{Id}")
    void deleteById(@Param("Id") long Id);

    @Select("select max(Id) from szjusercardgroupinfo " +
            "where DeletionStateCode = 0")
    long getMaxId();

    @Select("select max(Id) from szjusercardgroupinfo " +
            "where DeletionStateCode = 0")
    long getLastInsert();

    @Update("update szjusercardgroupinfo " +
            "set UserId = #{UserId} " +
            "where Id = #{Id}")
    void updateUserId(@Param("Id") long Id,
                      @Param("UserId") int UserId);

    @Update("update szjusercardgroupinfo " +
            "set Name = #{Name} " +
            "where Id = #{Id}")
    void updateName(@Param("Id") long Id,
                    @Param("Name") String Name);

    @Update("update szjusercardgroupinfo " +
            "set InvitationCode = #{InvitationCode} " +
            "where Id = #{Id}")
    void updateInvitationCode(@Param("Id") long Id,
                              @Param("InvitationCode") String InvitationCode);

}
