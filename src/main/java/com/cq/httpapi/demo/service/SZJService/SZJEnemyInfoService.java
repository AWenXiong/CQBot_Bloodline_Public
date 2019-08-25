package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetEnemyInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest.CreateEnemyInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetEnemyInfoResponseData;
import com.cq.httpapi.demo.dto.SZJ.Response.BackEndDataResponse.CreateEnemyInfoResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.BackEndDataResponse.GetAllEnemyInfoResponse;

public interface SZJEnemyInfoService {
    GetEnemyInfoResponseData getEnemyInfo(GetEnemyInfoRequest request);

    GetAllEnemyInfoResponse getAllEnemyInfo();

    boolean EnableEnemyInfo(Long enemyInfoId);

    CreateEnemyInfoResponse createEnemyInfo(CreateEnemyInfoRequest request);
}
