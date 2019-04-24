package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.entity.SZJ.Szjusercardinfo;

public interface SZJUserCardInfoService {
    long insert(Szjusercardinfo szjitemssex);

    long delete(Szjusercardinfo szjitemssex);

    long update(Szjusercardinfo szjitemssex);

    Szjusercardinfo getById(long id);
}
