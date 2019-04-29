package com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.util.ArrayList;

public class GetEnemyCardResponse extends SZJResponse {

    public ArrayList<GetEnemyCardResponseData> data;

    public void setData(ArrayList<GetEnemyCardResponseData> data) {
        this.data = data;
    }
}
