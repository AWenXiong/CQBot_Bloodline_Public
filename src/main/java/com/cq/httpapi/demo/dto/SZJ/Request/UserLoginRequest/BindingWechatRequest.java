package com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest;

public class BindingWechatRequest {
    public String Openid;
    public String WechatOpenid;

    @Override
    public String toString() {
        return "BindingWechatRequest{" +
                "Openid='" + Openid + '\'' +
                ", WechatOpenid='" + WechatOpenid + '\'' +
                '}';
    }

    public String getOpenid() {
        return Openid;
    }

    public void setOpenid(String openid) {
        Openid = openid;
    }

    public String getWechatOpenid() {
        return WechatOpenid;
    }

    public void setWechatOpenid(String wechatOpenid) {
        WechatOpenid = wechatOpenid;
    }
}
