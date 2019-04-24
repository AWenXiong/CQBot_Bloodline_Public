package com.cq.httpapi.demo.dao.CQdao;

import com.cq.httpapi.demo.entity.Purchase;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface PurchaseDao {

    @Select("select id, guild, service, end_time " +
            "from purchase")
    ArrayList<Purchase> getAllPurchase();

    @Select("select id, service, end_time " +
            "from purchase " +
            "where guild = #{userId}")
    ArrayList<Purchase> getPurchaseByUserId(@Param("userId") String userId);

    @Select("select guild, service, end_time " +
            "from purchase " +
            "where id = #{id}")
    Purchase getPurchaseById(@Param("id") Long id);

    @Select("select id " +
            "from purchase " +
            "where guild = #{userId} and service = #{service}")
    Long getPurchaseByUserIdAndService(@Param("userId") String userId,
                                       @Param("service") String service);

    @Insert("insert into purchase value()")
    void insertPurchase();

    @Update("update purchase " +
            "set guild = #{guild} " +
            "where id = #{id}")
    void updateGuildById(@Param("id") Long id,
                         @Param("guild") String guild);

    @Update("update purchase " +
            "set service = #{service} " +
            "where id = #{id}")
    void updateServiceById(@Param("id") Long id,
                           @Param("service") String service);

    @Update("update purchase " +
            "set end_time = #{endTime} " +
            "where id = #{id}")
    void updateEndTimeById(@Param("id") Long id,
                           @Param("endTime") String endTime);

    @Update("update purchase " +
            "set end_time = date_add(end_time, interval #{day} day) " +
            "where id = #{id}")
    void appendEndTimeById(@Param("id") Long id,
                           @Param("day") Long day);

    @Update("update purchase " +
            "set create_time = #{createTime}, create_user_id = #{createUserId}, description = #{desc} " +
            "where id = #{id}")
    void updateCreaterInfoById(@Param("id") Long id,
                               @Param("createTime") String createTime,
                               @Param("createUserId") String createUserId,
                               @Param("desc") String description);

    @Update("update purchase " +
            "set modified_time = #{modifiedTime}, modified_user_id = #{modifiedUserId}, description = #{desc} " +
            "where id = #{id}")
    void updateModifiedInfoById(@Param("id") Long id,
                                @Param("modifiedTime") String modifiedTime,
                                @Param("modifiedUserId") String modifiedUserId,
                                @Param("desc") String description);

    @Delete("delete from purchase " +
            "where id = #{id}")
    void deletePurchaseById(@Param("id") Long id);

    @Select("select max(id) from purchase")
    long getMaxId();

    @Select("select count(*) " +
            "from purchase " +
            "where guild = #{userId} and service = #{service} and end_time > now()")
    long existPurchase(@Param("userId") String userId,
                       @Param("service") String service);
}
