package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetEnemyCardRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetEnemyCardResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjenemycard;

import java.util.ArrayList;

public interface SZJEnemyCardService {
    ArrayList<GetEnemyCardResponseData> getEnemyInfo(GetEnemyCardRequest request);

    ArrayList<Szjenemycard> getEnemyCardByLevelId(Long levelId);
}
