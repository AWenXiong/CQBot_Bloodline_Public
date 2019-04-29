package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.entity.SZJ.Szjqueuelevel;

import java.util.ArrayList;

public interface SZJQueueLevelService {
    Long insertSzjqueuelevel(Long userId, Long queueInfoId, Double level, Long userSpellInfo);

    ArrayList<Szjqueuelevel> getQueueLevel(Long queueInfoId);
}
