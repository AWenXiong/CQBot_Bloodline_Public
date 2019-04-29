package com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest;

public class GetEnemyCardRequest {

    public String Openid;
    public Long Id;
    public Double Level;

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

    public Double getLevel() {
        return Level;
    }

    public void setLevel(Double level) {
        Level = level;
    }
}
