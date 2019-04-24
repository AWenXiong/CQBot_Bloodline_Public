package com.cq.httpapi.demo.dto.SZJ.Request.CardGroupRequest;

public class DeleteGroupRequest {

    public Long Id;
    public String Openid;

    @Override
    public String toString() {
        return "DeleteGroupRequest{" +
                "Id=" + Id +
                ", Openid='" + Openid + '\'' +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getOpenid() {
        return Openid;
    }

    public void setOpenid(String openid) {
        Openid = openid;
    }
}
