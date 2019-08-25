package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetEnemyLevelRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetEnemyLevelResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjenemylevel;

import java.util.ArrayList;

public interface SZJEnemyLevelService {
    ArrayList<GetEnemyLevelResponseData> getEnemyInfo(GetEnemyLevelRequest request);

    ArrayList<Szjenemylevel> getEnemyLevelsByInfoId(Long enemyInfoId);
}
