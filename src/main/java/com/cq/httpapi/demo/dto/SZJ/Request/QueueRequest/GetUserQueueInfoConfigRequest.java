package com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest;

public class GetUserQueueInfoConfigRequest {

    public String Openid;

    public int GroupId;

    @Override
    public String toString() {
        return "GetUserQueueInfoConfigRequest{" +
                "Openid='" + Openid + '\'' +
                ", GroupId=" + GroupId +
                '}';
    }

    public String getOpenid() {
        return Openid;
    }

    public void setOpenid(String openid) {
        Openid = openid;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }
}
