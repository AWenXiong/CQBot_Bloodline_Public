package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.entity.SZJ.Szjqueueinfo;

public interface SZJQueueInfoService {
    long insert(Szjqueueinfo szjitemssex);

    long delete(Szjqueueinfo szjitemssex);

    long update(Szjqueueinfo szjitemssex);

    Szjqueueinfo getById(long id);
}
