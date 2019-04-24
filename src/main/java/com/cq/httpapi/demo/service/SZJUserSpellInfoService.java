package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.entity.SZJ.Szjuserspellinfo;

public interface SZJUserSpellInfoService {
    long insert(Szjuserspellinfo szjitemssex);

    long delete(Szjuserspellinfo szjitemssex);

    long update(Szjuserspellinfo szjitemssex);

    Szjuserspellinfo getById(long id);
}
