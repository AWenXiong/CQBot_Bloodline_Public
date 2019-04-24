package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.entity.SZJ.Szjqueuecard;

public interface SZJQueueCardService {
    long insert(Szjqueuecard szjitemssex);

    long delete(Szjqueuecard szjitemssex);

    long update(Szjqueuecard szjitemssex);

    Szjqueuecard getById(long id);
}
