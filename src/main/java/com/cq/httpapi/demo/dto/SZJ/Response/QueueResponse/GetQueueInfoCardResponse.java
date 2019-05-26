package com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.util.ArrayList;

public class GetQueueInfoCardResponse extends SZJResponse {

    ArrayList<GetQueueInfoCardResponseData> data;

    public void setData(ArrayList<GetQueueInfoCardResponseData> data) {
        this.data = data;
    }

    /// 不能删，删了JSONObject就获取不了data了
    public ArrayList<GetQueueInfoCardResponseData> getData() {
        return data;
    }
}
