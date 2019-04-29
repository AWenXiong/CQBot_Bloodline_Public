package com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjuserspellinfo;
import com.cq.httpapi.demo.kit.ObjectKit;

import java.sql.Timestamp;

public class GetUserSpellsInfoResponseData {

    public Long Id;
    public Long UserId;
    public Long GroupId;
    public Long SpellId;
    public Long FightingCapacity;
    public String Description;
    public Long DeletionStateCode;
    public Long SortCode;
    public Timestamp CreateOn;
    public String CreateUserId;
    public String CreateBy;
    public Timestamp ModifiedOn;
    public String ModifiedUserId;
    public String ModifiedBy;

    public GetUserSpellsInfoResponseData(Szjuserspellinfo szjuserspellinfo) throws Exception {
        ObjectKit.deliverPropIgnoreCase(this, szjuserspellinfo);
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public void setGroupId(Long groupId) {
        GroupId = groupId;
    }

    public void setSpellId(Long spellId) {
        SpellId = spellId;
    }

    public void setFightingCapacity(Long fightingCapacity) {
        FightingCapacity = fightingCapacity;
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
