package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetEnemyInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetEnemyInfoResponseData;

public interface SZJEnemyInfoService {
    GetEnemyInfoResponseData getEnemyInfo(GetEnemyInfoRequest request);
}
