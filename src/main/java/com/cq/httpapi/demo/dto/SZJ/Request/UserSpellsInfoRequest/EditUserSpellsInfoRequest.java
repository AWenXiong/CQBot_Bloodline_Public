package com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest;

import java.util.ArrayList;

public class EditUserSpellsInfoRequest {

    public String Openid;
    public Long Id;
    public ArrayList<EditUserSpellsInfoRequestData> UserSpellInfo;

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

    public ArrayList<EditUserSpellsInfoRequestData> getUserSpellInfo() {
        return UserSpellInfo;
    }

    public void setUserSpellInfo(ArrayList<EditUserSpellsInfoRequestData> userSpellInfo) {
        UserSpellInfo = userSpellInfo;
    }
}
