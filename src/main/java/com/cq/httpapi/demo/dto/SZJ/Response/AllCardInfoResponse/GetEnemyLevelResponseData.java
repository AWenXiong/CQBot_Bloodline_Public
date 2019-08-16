package com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjenemylevel;
import com.cq.httpapi.demo.kit.ObjectKit;

import java.sql.Timestamp;

public class GetEnemyLevelResponseData {
    public Long Id;
    public Long EnemyInfoId;
    public Double Level;
    public Long FightingCapacity;
    public String AdditionType;
    public String AdditionOption;
    public Double AdditionAmmount;
    public String Description;
    public Long DeletionStateCode;
    public Long SortCode;
    public Timestamp CreateOn;
    public String CreateUserId;
    public String CreateBy;
    public Timestamp ModifiedOn;
    public String ModifiedUserId;
    public String ModifiedBy;
    public Long MaxNum;

    public void setMaxNum(Long maxNum) {
        MaxNum = maxNum;
    }

    public GetEnemyLevelResponseData(Szjenemylevel szjenemylevel) throws Exception {
        ObjectKit.deliverPropIgnoreCase(this, szjenemylevel);
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setEnemyInfoId(Long enemyInfoId) {
        EnemyInfoId = enemyInfoId;
    }

    public void setLevel(Double level) {
        Level = level;
    }

    public void setFightingCapacity(Long fightingCapacity) {
        FightingCapacity = fightingCapacity;
    }

    public void setAdditionType(String additionType) {
        AdditionType = additionType;
    }

    public void setAdditionOption(String additionOption) {
        AdditionOption = additionOption;
    }

    public void setAdditionAmmount(Double additionAmmount) {
        AdditionAmmount = additionAmmount;
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
