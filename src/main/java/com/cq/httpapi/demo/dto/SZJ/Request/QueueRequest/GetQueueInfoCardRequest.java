package com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest;

public class GetQueueInfoCardRequest {

    public String Openid;
    public Long GroupId;

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
}
