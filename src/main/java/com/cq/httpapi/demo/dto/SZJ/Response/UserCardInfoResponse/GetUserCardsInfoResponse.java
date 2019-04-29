package com.cq.httpapi.demo.dto.SZJ.Response.UserCardInfoResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.util.ArrayList;

public class GetUserCardsInfoResponse extends SZJResponse {

    public ArrayList<GetUserCardsInfoResponseData> data;

    public void setData(ArrayList<GetUserCardsInfoResponseData> data) {
        this.data = data;
    }
}
