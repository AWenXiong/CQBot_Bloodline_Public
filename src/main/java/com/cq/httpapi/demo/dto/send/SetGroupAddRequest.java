package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class SetGroupAddRequest extends Response {

    private String flag;
    private String sub_type;
    private boolean approve = true;
    private String reason;

    @Override
    public void execute() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", this.getFlag());
            jsonObject.put("sub_type", this.getSub_type());
            jsonObject.put("approve", this.isApprove());
            jsonObject.put("reason", this.getReason());
            UrlKit.sendPost(ApiPath.SET_GROUP_ADD_REQUEST, jsonObject);
        } catch (Exception e) {

        }
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
