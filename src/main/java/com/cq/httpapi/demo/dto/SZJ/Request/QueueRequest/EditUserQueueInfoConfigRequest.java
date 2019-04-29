package com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest;

import java.util.ArrayList;

public class EditUserQueueInfoConfigRequest {

    public String Openid;
    public Long Id;
    public ArrayList<EditUserQueueInfoConfigRequestData> UserQueueInfoConfig;

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

    public ArrayList<EditUserQueueInfoConfigRequestData> getUserQueueInfoConfig() {
        return UserQueueInfoConfig;
    }

    public void setUserQueueInfoConfig(ArrayList<EditUserQueueInfoConfigRequestData> userQueueInfoConfig) {
        UserQueueInfoConfig = userQueueInfoConfig;
    }
}
