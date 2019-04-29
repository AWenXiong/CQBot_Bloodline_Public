package com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.util.ArrayList;

public class GetEnemyLevelResponse extends SZJResponse {

    public ArrayList<GetEnemyLevelResponseData> data;

    public void setData(ArrayList<GetEnemyLevelResponseData> data) {
        this.data = data;
    }
}
