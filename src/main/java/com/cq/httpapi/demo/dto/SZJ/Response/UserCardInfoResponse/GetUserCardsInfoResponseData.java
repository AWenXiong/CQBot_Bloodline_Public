package com.cq.httpapi.demo.dto.SZJ.Response.UserCardInfoResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjusercardinfo;
import com.cq.httpapi.demo.kit.ObjectKit;

import java.sql.Timestamp;

public class GetUserCardsInfoResponseData {

    public Long Id;
    public Long CardInfoId;
    public Long UserId;
    public Long GroupId;
    public Long FightingCapacity;
    public Long Fate;
    public Long IsGodofWar;
    public String Description;
    public Long DeletionStateCode;
    public Long SortCode;
    public Timestamp CreateOn;
    public String CreateUserId;
    public String CreateBy;
    public Timestamp ModifiedOn;
    public String ModifiedUserId;
    public String ModifiedBy;

    public GetUserCardsInfoResponseData(Szjusercardinfo szjusercardinfo) throws Exception {
        ObjectKit.deliverPropIgnoreCase(this, szjusercardinfo);
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setCardInfoId(Long cardInfoId) {
        CardInfoId = cardInfoId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public void setGroupId(Long groupId) {
        GroupId = groupId;
    }

    public void setFightingCapacity(Long fightingCapacity) {
        FightingCapacity = fightingCapacity;
    }

    public void setFate(Long fate) {
        Fate = fate;
    }

    public void setIsGodofWar(Long isGodofWar) {
        IsGodofWar = isGodofWar;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setDeletionStateCode(Long deletionStateCode) {
        DeletionStateCode = deletionStateCode;
    }

    public void setSortCode(Long sortCode) {
        SortCode = sortCode;
    }

    public void setCreateOn(Timestamp createOn) {
        CreateOn = createOn;
    }

    public void setCreateUserId(String createUserId) {
        CreateUserId = createUserId;
    }

    public void setCreateBy(String createBy) {
        CreateBy = createBy;
    }

    public void setModifiedOn(Timestamp modifiedOn) {
        ModifiedOn = modifiedOn;
    }

    public void setModifiedUserId(String modifiedUserId) {
        ModifiedUserId = modifiedUserId;
    }

    public void setModifiedBy(String modifiedBy) {
        ModifiedBy = modifiedBy;
    }
}
