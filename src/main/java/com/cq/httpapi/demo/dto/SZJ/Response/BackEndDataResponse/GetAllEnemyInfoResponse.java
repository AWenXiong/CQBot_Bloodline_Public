package com.cq.httpapi.demo.dto.SZJ.Response.BackEndDataResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.util.ArrayList;

public class GetAllEnemyInfoResponse extends SZJResponse {

    public ArrayList<GetAllEnemyInfoResponseData> data;

    public void setData(ArrayList<GetAllEnemyInfoResponseData> data) {
        this.data = data;
    }
}
