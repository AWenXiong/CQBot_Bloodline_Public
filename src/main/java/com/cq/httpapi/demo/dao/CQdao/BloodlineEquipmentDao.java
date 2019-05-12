package com.cq.httpapi.demo.dao.CQdao;

import com.cq.httpapi.demo.entity.CQ.BloodlineEquipment;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface BloodlineEquipmentDao {

    @Select("select id, cardId, equipmentId, type " +
            "from bloodline_equipment " +
            "where cardId = #{cardId} and type = #{type}")
    BloodlineEquipment getByCardIdAndType(@Param("cardId") long cardId,
                                          @Param("type") String type);

    @Select("select id, cardId, equipmentId, type " +
            "from bloodline_equipment " +
            "where cardId = #{cardId}")
    ArrayList<BloodlineEquipment> getByCardId(@Param("cardId") long cardId);

    @Select("select count(*) " +
            "from bloodline_equipment " +
            "where cardId = #{cardId} and type = #{type}")
    long existEquipmentRecord(@Param("cardId") long cardId,
                              @Param("type") String type);

    @Insert("insert into bloodline_equipment(cardId, equipmentId, type) value " +
            "(#{cardId}, #{equipmentId}, #{type})")
    void createBloodlineEquipment(@Param("cardId") long cardId,
                                  @Param("equipmentId") long equipmentId,
                                  @Param("type") String type);

    @Delete("delete from bloodline_equipment " +
            "where id = #{id}")
    void deleteById(@Param("id") long id);

    @Delete("delete from bloodline_equipment " +
            "where cardId = #{cardId}")
    void deleteByCardId(@Param("cardId") long cardId);

    @Delete("delete from bloodline_equipment " +
            "where equipmentId = #{equipmentId}")
    void deleteByEquipmentId(@Param("equipmentId") long equipmentId);

    @Update("update bloodline_equipment " +
            "set cardId = #{cardId} " +
            "where id = #{id}")
    void updateCardId(@Param("id") long id,
                      @Param("cardId") long cardId);

    @Update("update bloodline_equipment " +
            "set equipmentId = #{equipmentId} " +
            "where id = #{id}")
    void updateEquipmentId(@Param("id") long id,
                           @Param("equipmentId") long equipmentId);

    @Update("update bloodline_equipment " +
            "set type = #{type} " +
            "where id = #{id}")
    void updateType(@Param("id") long id,
                    @Param("type") String type);
}
