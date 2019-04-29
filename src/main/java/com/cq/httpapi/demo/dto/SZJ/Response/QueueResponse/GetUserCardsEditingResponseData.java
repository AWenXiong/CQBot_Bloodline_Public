package com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjusercardsediting;
import com.cq.httpapi.demo.kit.ObjectKit;

import java.sql.Timestamp;

public class GetUserCardsEditingResponseData {

    public Long Id;
    public Long UserId;
    public Long GroupId;
    public Long CardInfoId;
    public Double OriginLevel;
    public Double OriginPosition;
    public String Description;
    public Long DeletionStateCode;
    public Long SortCode;
    public Timestamp CreateOn;
    public String CreateUserId;
    public String CreateBy;
    public Timestamp ModifiedOn;
    public String ModifiedUserId;
    public String ModifiedBy;

    public GetUserCardsEditingResponseData(Szjusercardsediting szjusercardsediting) throws Exception {
        ObjectKit.deliverPropIgnoreCase(this, szjusercardsediting);
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

    public void setCardInfoId(Long cardInfoId) {
        CardInfoId = cardInfoId;
    }

    public void setOriginLevel(Double originLevel) {
        OriginLevel = originLevel;
    }

    public void setOriginPosition(Double originPosition) {
        OriginPosition = originPosition;
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
