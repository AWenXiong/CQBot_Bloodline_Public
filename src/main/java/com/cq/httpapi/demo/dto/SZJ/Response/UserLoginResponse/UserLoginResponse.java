package com.cq.httpapi.demo.dto.SZJ.Response.UserLoginResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

public class UserLoginResponse extends SZJResponse {

    public UserLoginResponseData data;

    @Override
    public String toString() {
        return "UserLoginResponse{" +
                "data=" + data +
                '}';
    }

    public UserLoginResponseData getData() {
        return data;
    }

    public void setData(UserLoginResponseData data) {
        this.data = data;
    }
}
