package com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjcardinfoothername;
import com.cq.httpapi.demo.kit.ObjectKit;

import java.sql.Timestamp;

public class GetAllCardsOtherNameResponseData {
    public Long Id;
    public Long CardInfoId;
    public String Name;
    public String Description;
    public Long DeletionStateCode;
    public Long SortCode;
    public Timestamp CreateOn;
    public String CreateUserId;
    public String CreateBy;
    public Timestamp ModifiedOn;
    public String ModifiedUserId;
    public String ModifiedBy;

    public GetAllCardsOtherNameResponseData(Szjcardinfoothername szjcardinfoothername) throws Exception {
        ObjectKit.deliverPropIgnoreCase(this, szjcardinfoothername);
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setCardInfoId(Long cardInfoId) {
        CardInfoId = cardInfoId;
    }

    public void setName(String name) {
        Name = name;
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
