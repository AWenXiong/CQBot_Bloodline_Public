package com.cq.httpapi.demo.dto.SZJ.Response.CardGroupResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjusercardgroupinfo;

public class GetAllGroupsResponseData {

    public Long Id;
    public String Name;
    public String InvitationCode;

    public GetAllGroupsResponseData(Szjusercardgroupinfo szjusercardgroupinfo) {
        this.Id = szjusercardgroupinfo.getId();
        this.Name = szjusercardgroupinfo.getName();
        this.InvitationCode = szjusercardgroupinfo.getInvitationCode();
    }

    @Override
    public String toString() {
        return "GetAllGroupsResponseData{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", InvitationCode='" + InvitationCode + '\'' +
                '}';
    }
}
