package com.cq.httpapi.demo.dto.SZJ.Response.CardGroupResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.util.ArrayList;

public class GetAllGroupsResponse extends SZJResponse {

    public ArrayList<GetAllGroupsResponseData> data;

    public ArrayList<GetAllGroupsResponseData> getData() {
        return data;
    }

    public void setData(ArrayList<GetAllGroupsResponseData> data) {
        this.data = data;
    }
}
