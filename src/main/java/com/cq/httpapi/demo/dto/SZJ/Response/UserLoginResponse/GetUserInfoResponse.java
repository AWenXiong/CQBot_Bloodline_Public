package com.cq.httpapi.demo.dto.SZJ.Response.UserLoginResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

public class GetUserInfoResponse extends SZJResponse {

    public GetUserInfoResponseData data;

    public GetUserInfoResponseData getData() {
        return data;
    }

    public void setData(GetUserInfoResponseData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetUserInfoResponse{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", errorCode=" + errorCode +
                ", success=" + success +
                '}';
    }
}
