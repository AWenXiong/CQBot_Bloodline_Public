package com.cq.httpapi.demo.dao.CQdao;

import com.cq.httpapi.demo.entity.CQ.Partner;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface PartnerDao {

    @Select("select card_id from partner " +
            "where partner1 = #{id} or " +
            "partner2 = #{id} or " +
            "partner3 = #{id}")
    ArrayList<Partner> getMasters(@Param("id") long id);

    @Select("select count(*) from partner " +
            "where card_id = #{id}")
    int countRecord(@Param("id") long id);

    @Select("select max(id) from partner")
    int getLastInsert();

    @Insert("insert into partner(card_id, partner1, partner2, partner3) value" +
            "(#{p.cardId}, #{p.partner1}, #{p.partner2}, #{p.partner3})")
    void insert(@Param("p") Partner partner);

    @Delete("delete from partner " +
            "where card_id = #{cardId}")
    void delete(@Param("cardId") long cardId);

    @Update("update partner set " +
            "create_time = #{createTime} " +
            "where id = #{id} ")
    void updateCreateTime(@Param("id") long id,
                          @Param("createTime") String createTime);

    @Update("update partner set " +
            "create_user_id = #{userId} " +
            "where id = #{id}")
    void updateCreateUserId(@Param("id") long id,
                            @Param("userId") String userId);

    @Update("update partner set " +
            "description = #{desc} " +
            "where id = #{id}")
    void updateDescription(@Param("id") long id,
                           @Param("desc") String desc);
}
