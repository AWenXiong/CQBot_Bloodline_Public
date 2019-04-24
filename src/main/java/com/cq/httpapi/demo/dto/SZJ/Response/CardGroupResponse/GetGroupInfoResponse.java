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

    public Long getExtraFrequency() {
        return ExtraFrequency;
    }

    public void setExtraFrequency(Long extraFrequency) {
        ExtraFrequency = extraFrequency;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Timestamp getDueTime() {
        return DueTime;
    }

    public void setDueTime(Timestamp dueTime) {
        DueTime = dueTime;
    }

    public Double getEffectiveTime() {
        return EffectiveTime;
    }

    public void setEffectiveTime(Double effectiveTime) {
        EffectiveTime = effectiveTime;
    }
}
