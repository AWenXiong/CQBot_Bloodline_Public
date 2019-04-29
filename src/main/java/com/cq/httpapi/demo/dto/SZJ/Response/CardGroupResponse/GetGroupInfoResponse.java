package com.cq.httpapi.demo.dto.SZJ.Response.CardGroupResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

import java.sql.Timestamp;

public class GetGroupInfoResponse extends SZJResponse {

    public Long Id;
    public String Name;
    public String InvitationCode;
    public String Type;
    public Timestamp DueTime;
    public Double EffectiveTime;
    public Long ExtraFrequency;

}
