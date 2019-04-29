package com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.util.ArrayList;

public class GetUserCardsEditingResponse extends SZJResponse {

    public ArrayList<GetUserCardsEditingResponseData> data;

    public void setData(ArrayList<GetUserCardsEditingResponseData> data) {
        this.data = data;
    }
}
