package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.entity.SZJ.Szjitemscamp;

public interface SZJItemsCampService {

    long insert(Szjitemscamp szjitemssex);

    long delete(Szjitemscamp szjitemssex);

    long update(Szjitemscamp szjitemssex);

    Szjitemscamp getById(long id);

}
