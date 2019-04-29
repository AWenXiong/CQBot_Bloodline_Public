package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.CreateQueueInfoRequest;
import com.cq.httpapi.demo.entity.SZJ.Szjqueueinfo;

public interface SZJQueueInfoService {

    boolean createQueueInfo(CreateQueueInfoRequest request);

    Long insertQueueInfo(Long userId, Long groupId);

    Szjqueueinfo getQueueInfo(Long userId, Long groupId);
}
