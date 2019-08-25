package com.cq.httpapi.demo.dto.SZJ.Response.BackEndDataResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjenemyinfo;
import com.cq.httpapi.demo.kit.ObjectKit;

import java.io.Serializable;
import java.sql.Timestamp;

public class GetAllEnemyInfoResponseData implements Serializable {

    public Long Id;
    public String Code;
    public String Name;
    public Long Enabled;
    public String Description;
    public Long SortCode;
    public Timestamp CreateOn;
    public String CreateUserId;
    public String CreateBy;
    public Timestamp ModifiedOn;
    public String ModifiedUserId;
    public String ModifiedUserBy;

    public void setEnemyInfo(Szjenemyinfo szjenemyinfo) throws Exception {
        ObjectKit.deliverPropIgnoreCase(this, szjenemyinfo);
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setCode(String code) {
        Code = code;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEnabled(Long enabled) {
        Enabled = enabled;
    }

    public void setDescription(String description) {
        Description = description;
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

    public void setModifiedUserBy(String modifiedUserBy) {
        ModifiedUserBy = modifiedUserBy;
    }
}
