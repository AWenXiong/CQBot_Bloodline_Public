package com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.util.ArrayList;

public class GetUserQueueInfoConfigResponse extends SZJResponse {

    public ArrayList<GetUserQueueInfoConfigResponseData> data;

    public ArrayList<GetUserQueueInfoConfigResponseData> getData() {
        return data;
    }

    public void setData(ArrayList<GetUserQueueInfoConfigResponseData> data) {
        this.data = data;
    }
}
