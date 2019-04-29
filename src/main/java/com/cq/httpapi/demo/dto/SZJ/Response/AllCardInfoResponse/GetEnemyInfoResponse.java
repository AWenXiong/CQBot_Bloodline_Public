package com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.util.ArrayList;

public class GetEnemyInfoResponse extends SZJResponse {

    public ArrayList<GetEnemyInfoResponseData> data;

    public void setData(ArrayList<GetEnemyInfoResponseData> data) {
        this.data = data;
    }
}
