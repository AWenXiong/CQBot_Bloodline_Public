package com.cq.httpapi.demo.dao.STZBDao;

import com.cq.httpapi.demo.entity.STZB.StzbSkill;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SkillDao {

    @Select("select * from stzb_skill where id = #{id}")
    StzbSkill getSkill(@Param("id") Long id);

    @Select("select count(1) from stzb_skill where skillName = #{skillName}")
    int existSkill(@Param("skillName") String skillName);

    @Select("select count(1) from stzb_skill where skillName regexp #{skillName}")
    int existSkillLike(@Param("skillName") String skillName);

    @Select("select id from stzb_skill where skillName = #{skillName}")
    long getSkillIdByName(@Param("skillName") String skillName);
}
