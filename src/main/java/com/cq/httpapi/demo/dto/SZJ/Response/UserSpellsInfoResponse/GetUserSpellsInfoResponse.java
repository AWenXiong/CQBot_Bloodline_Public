package com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.util.ArrayList;

public class GetUserSpellsInfoResponse extends SZJResponse {

    public ArrayList<GetUserSpellsInfoResponseData> data;

    public void setData(ArrayList<GetUserSpellsInfoResponseData> data) {
        this.data = data;
    }
}
