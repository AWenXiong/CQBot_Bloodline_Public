package com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.util.ArrayList;

public class GetAllSpellsInfoResponse extends SZJResponse {

    public ArrayList<GetAllSpellsInfoResponseData> data;

    public void setData(ArrayList<GetAllSpellsInfoResponseData> data) {
        this.data = data;
    }
}
