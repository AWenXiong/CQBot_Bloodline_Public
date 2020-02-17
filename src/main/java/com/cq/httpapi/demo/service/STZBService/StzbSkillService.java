package com.cq.httpapi.demo.service.STZBService;

import com.cq.httpapi.demo.entity.STZB.StzbSkill;

public interface StzbSkillService {

    StzbSkill getSkillByName(String skillName);

    boolean existSkill(String skillName);

}
