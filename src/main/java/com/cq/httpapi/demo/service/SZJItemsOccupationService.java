package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.entity.SZJ.Szjitemsoccupation;

public interface SZJItemsOccupationService {

    long insert(Szjitemsoccupation szjitemssex);

    long delete(Szjitemsoccupation szjitemssex);

    long update(Szjitemsoccupation szjitemssex);

    Szjitemsoccupation getById(long id);

}
