package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.entity.SZJ.Szjqueuelevel;

public interface SZJQueueLevelService {
    long insert(Szjqueuelevel szjitemssex);

    long delete(Szjqueuelevel szjitemssex);

    long update(Szjqueuelevel szjitemssex);

    Szjqueuelevel getById(long id);
}
