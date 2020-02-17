package com.cq.httpapi.demo.service.impl.STZBServiceImpl;

import com.cq.httpapi.demo.dao.STZBDao.SkillDao;
import com.cq.httpapi.demo.entity.STZB.StzbSkill;
import com.cq.httpapi.demo.service.STZBService.StzbSkillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StzbSkillServiceImpl implements StzbSkillService {

    @Resource
    SkillDao skillDao;

    @Override
    public StzbSkill getSkillByName(String skillName) {
        long id = skillDao.getSkillIdByName(skillName);
        return skillDao.getSkill(id);
    }

    @Override
    public boolean existSkill(String skillName) {
        return skillDao.existSkill(skillName) == 1;
    }
}
