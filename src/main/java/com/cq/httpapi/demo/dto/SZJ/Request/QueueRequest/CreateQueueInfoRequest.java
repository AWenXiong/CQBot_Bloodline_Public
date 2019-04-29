package com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest;

import java.util.ArrayList;

public class CreateQueueInfoRequest {

    public String Openid;
    public Long GroupId;
    public ArrayList<CreateQueueInfoRequestData> Level;

    public String getOpenid() {
        return Openid;
    }

    public void setOpenid(String openid) {
        Openid = openid;
    }

    public Long getGroupId() {
        return GroupId;
    }

    public void setGroupId(Long groupId) {
        GroupId = groupId;
    }

    public ArrayList<CreateQueueInfoRequestData> getLevel() {
        return Level;
    }

    public void setLevel(ArrayList<CreateQueueInfoRequestData> level) {
        Level = level;
    }
}
