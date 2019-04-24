package com.cq.httpapi.demo.dto.send;

import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class GetStatus extends Response {

    @Override
    public void execute() {
        try {
            UrlKit.sendPost(ApiPath.GET_STATUS, null);
        } catch (Exception e) {

        }
    }
}
