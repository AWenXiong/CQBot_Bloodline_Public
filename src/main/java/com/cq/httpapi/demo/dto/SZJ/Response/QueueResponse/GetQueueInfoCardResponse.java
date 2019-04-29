package com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.util.ArrayList;

public class GetQueueInfoCardResponse extends SZJResponse {

    ArrayList<GetQueueInfoCardResponseData> data;

    public void setData(ArrayList<GetQueueInfoCardResponseData> data) {
        this.data = data;
    }
}
