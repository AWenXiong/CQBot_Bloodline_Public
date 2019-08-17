package com.cq.httpapi.demo.dto.send;

import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class GetStatus extends Response {

    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public void execute() {
        try {
            UrlKit.sendPost(this.ip + ApiPath.GET_STATUS.getUrlPath(), null);
        } catch (Exception e) {

        }
    }
}
