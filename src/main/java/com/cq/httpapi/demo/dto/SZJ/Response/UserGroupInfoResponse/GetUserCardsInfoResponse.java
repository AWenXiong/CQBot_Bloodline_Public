package com.cq.httpapi.demo.dto.SZJ.Response.UserGroupInfoResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

public class GetUserCardsInfoResponse extends SZJResponse {

    public Object data;

    @Override
    public String toString() {
        return "GetUserCardsInfoResponse{" +
                "data=" + data +
                '}';
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
