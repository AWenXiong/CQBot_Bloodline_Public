package com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest;

public class DeleteUserSpellRequest {

    public String Openid;
    public Long Id;

    public String getOpenid() {
        return Openid;
    }

    public void setOpenid(String openid) {
        Openid = openid;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
