package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetEnemyInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetEnemyInfoResponseData;

import java.util.ArrayList;

public interface SZJEnemyInfoService {
    ArrayList<GetEnemyInfoResponseData> getEnemyInfo(GetEnemyInfoRequest request);
}
