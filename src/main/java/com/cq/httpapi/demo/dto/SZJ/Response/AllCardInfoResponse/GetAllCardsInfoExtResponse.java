package com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.util.ArrayList;

public class GetAllCardsInfoExtResponse extends SZJResponse {

    public ArrayList<GetAllCardsInfoExtResponseData> data;

    public ArrayList<GetAllCardsInfoExtResponseData> getData() {
        return data;
    }

    public void setData(ArrayList<GetAllCardsInfoExtResponseData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetAllCardsInfoExtResponse{" +
                "data=" + data +
                '}';
    }
}
