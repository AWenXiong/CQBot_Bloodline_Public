package com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

public class GetAllCardsOtherNameResponse extends SZJResponse {

    public Object data;

    @Override
    public String toString() {
        return "GetAllCardsOtherNameResponse{" +
                "date=" + data +
                '}';
    }

    public Object getData() {
        return data;
    }

    public void setData(Object date) {
        this.data = date;
    }
}
