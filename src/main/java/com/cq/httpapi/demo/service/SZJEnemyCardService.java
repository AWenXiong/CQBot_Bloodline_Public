package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.entity.SZJ.Szjenemycard;

public interface SZJEnemyCardService {
    long insert(Szjenemycard szjitemssex);

    long delete(Szjenemycard szjitemssex);

    long update(Szjenemycard szjitemssex);

    Szjenemycard getById(long id);
}
