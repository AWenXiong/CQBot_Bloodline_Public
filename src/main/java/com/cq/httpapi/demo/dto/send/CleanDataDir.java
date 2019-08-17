package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class CleanDataDir extends Response {

    private String ip;
    private String data_dir;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public void execute() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data_dir", this.getData_dir());
            UrlKit.sendPost(this.ip + ApiPath.CLEAN_DATA_DIR.getUrlPath(), jsonObject);
        } catch (Exception e) {

        }
    }

    public String getData_dir() {
        return data_dir;
    }

    public void setData_dir(String data_dir) {
        this.data_dir = data_dir;
    }
}
