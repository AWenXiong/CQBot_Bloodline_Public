package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.EditUserQueueInfoConfigRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.GetUserQueueInfoConfigRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse.GetUserQueueInfoConfigResponseData;

import java.util.ArrayList;

public interface SZJQueueInfoConfigSerivce {

    ArrayList<GetUserQueueInfoConfigResponseData> getUserQueueInfoConfig(GetUserQueueInfoConfigRequest request);

    boolean editUserQueueInfoConfig(EditUserQueueInfoConfigRequest request);

}
