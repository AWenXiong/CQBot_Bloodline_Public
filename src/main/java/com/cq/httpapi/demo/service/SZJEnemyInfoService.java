package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.entity.SZJ.Szjenemyinfo;

public interface SZJEnemyInfoService {
    long insert(Szjenemyinfo szjitemssex);

    long delete(Szjenemyinfo szjitemssex);

    long update(Szjenemyinfo szjitemssex);

    Szjenemyinfo getById(long id);
}
