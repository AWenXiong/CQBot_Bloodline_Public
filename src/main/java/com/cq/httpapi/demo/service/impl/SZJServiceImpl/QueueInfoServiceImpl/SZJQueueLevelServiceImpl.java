package com.cq.httpapi.demo.service.impl.SZJServiceImpl.QueueInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjqueuelevelDao;
import com.cq.httpapi.demo.entity.SZJ.Szjqueuelevel;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.SZJService.SZJQueueLevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJQueueLevelServiceImpl implements SZJQueueLevelService {

    @Resource
    private SzjqueuelevelDao szjqueuelevelDao;

    @Override
    public Long insertSzjqueuelevel(Long userId, Long queueInfoId, Double level, Long userSpellInfo) {

        try {
            // 插入新的数据到 SZJQueueLevel
            if (userSpellInfo != null) {
                szjqueuelevelDao.insertSzjqueuelevel(queueInfoId, level, userSpellInfo);
            } else {
                szjqueuelevelDao.insertSzjqueuelevelNullSpell(queueInfoId, level);
            }

            // 更新相关信息
            Long newLevelId = szjqueuelevelDao.getLastInsert(queueInfoId);
            szjqueuelevelDao.updateCreateInfo(newLevelId, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
            szjqueuelevelDao.updateDescription(newLevelId, "新增新的自动配队");
            return newLevelId;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ArrayList<Szjqueuelevel> getQueueLevel(Long queueInfoId) {
        if (queueInfoId == null) {
            return null;
        }

        try {
            ArrayList<Szjqueuelevel> res = szjqueuelevelDao.getQueueLevel(queueInfoId);
            ;
            return res;
        } catch (Exception e) {
            throw e;
        }

    }
}
