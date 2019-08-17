package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;

public class SetFriendAddRequest extends Response {

    private String flag;
    private boolean approve = true;
    private String remark;
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
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", this.getFlag());
            jsonObject.put("approve", this.isApprove());
            jsonObject.put("remark", this.getRemark());
            UrlKit.sendPost(this.ip + ApiPath.SET_FRIEND_ADD_REQUEST.getUrlPath(), jsonObject);
        } catch (Exception e) {

        }
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
