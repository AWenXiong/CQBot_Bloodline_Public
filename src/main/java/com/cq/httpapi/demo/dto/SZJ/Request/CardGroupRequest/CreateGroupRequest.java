package com.cq.httpapi.demo.dto.SZJ.Request.CardGroupRequest;

public class CreateGroupRequest {

    public String Openid;
    public String Name;
    public String InvitationCode;

    public String getOpenid() {
        return Openid;
    }

    public void setOpenid(String openid) {
        Openid = openid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getInvitationCode() {
        return InvitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        InvitationCode = invitationCode;
    }

    @Override
    public String toString() {
        return "CreateGroupRequest{" +
                "Openid='" + Openid + '\'' +
                ", Name='" + Name + '\'' +
                ", InvitationCode='" + InvitationCode + '\'' +
                '}';
    }
}
