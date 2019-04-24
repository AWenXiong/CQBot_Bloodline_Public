package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.entity.SZJ.Szjspellinfo;

public interface SZJSpellInfoService {
    long insert(Szjspellinfo szjitemssex);

    long delete(Szjspellinfo szjitemssex);

    long update(Szjspellinfo szjitemssex);

    Szjspellinfo getById(long id);
}
