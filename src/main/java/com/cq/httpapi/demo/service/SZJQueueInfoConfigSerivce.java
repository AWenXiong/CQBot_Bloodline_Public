package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.entity.SZJ.Szjqueueinfoconfig;

public interface SZJQueueInfoConfigSerivce {
    long insert(Szjqueueinfoconfig szjitemssex);

    long delete(Szjqueueinfoconfig szjitemssex);

    long update(Szjqueueinfoconfig szjitemssex);

    Szjqueueinfoconfig getById(long id);
}
