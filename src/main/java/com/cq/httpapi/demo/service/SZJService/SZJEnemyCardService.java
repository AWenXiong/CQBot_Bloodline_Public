package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetEnemyCardRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetEnemyCardResponseData;

import java.util.ArrayList;

public interface SZJEnemyCardService {
    ArrayList<GetEnemyCardResponseData> getEnemyInfo(GetEnemyCardRequest request);
}
