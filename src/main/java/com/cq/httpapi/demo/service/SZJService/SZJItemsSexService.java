package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.entity.SZJ.Szjitemssex;

public interface SZJItemsSexService {

    long insert(Szjitemssex szjitemssex);

    long delete(Szjitemssex szjitemssex);

    long update(Szjitemssex szjitemssex);

    Szjitemssex getById(long id);
}
