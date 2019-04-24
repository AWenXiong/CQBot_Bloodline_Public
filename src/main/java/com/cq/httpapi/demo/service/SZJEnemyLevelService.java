package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.entity.SZJ.Szjenemylevel;

public interface SZJEnemyLevelService {
    long insert(Szjenemylevel szjitemssex);

    long delete(Szjenemylevel szjitemssex);

    long update(Szjenemylevel szjitemssex);

    Szjenemylevel getById(long id);
}
