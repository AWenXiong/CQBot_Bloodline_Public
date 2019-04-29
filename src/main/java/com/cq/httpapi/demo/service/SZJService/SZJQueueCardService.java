package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.EditUserQueueCardRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.GetQueueInfoCardRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse.GetQueueInfoCardResponseData;

import java.util.ArrayList;

public interface SZJQueueCardService {
    Long insertSzjqueuecard(Long userId, Long queueInfoId, Double level, Long cardInfoId, Double position);

    ArrayList<GetQueueInfoCardResponseData> getQueueInfoCard(GetQueueInfoCardRequest request);

    boolean editUserQueueCard(EditUserQueueCardRequest request);
}
