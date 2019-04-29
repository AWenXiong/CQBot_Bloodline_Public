package com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.util.ArrayList;

public class GetAllCardsOtherNameResponse extends SZJResponse {

    public ArrayList<GetAllCardsOtherNameResponseData> data;

    public ArrayList<GetAllCardsOtherNameResponseData> getData() {
        return data;
    }

    public void setData(ArrayList<GetAllCardsOtherNameResponseData> date) {
        this.data = date;
    }
}
