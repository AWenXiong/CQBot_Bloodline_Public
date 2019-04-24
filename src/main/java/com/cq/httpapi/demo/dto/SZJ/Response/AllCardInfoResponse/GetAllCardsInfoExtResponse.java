package com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

public class GetAllCardsInfoExtResponse extends SZJResponse {

    public Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetAllCardsInfoExtResponse{" +
                "data=" + data +
                '}';
    }
}
