package com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjspellinfo;
import com.cq.httpapi.demo.kit.ObjectKit;

import java.sql.Timestamp;

public class GetAllSpellsInfoResponseData {

    public Long Id;
    public String Code;
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

    public GetAllSpellsInfoResponseData(Szjspellinfo szjspellinfo) throws Exception {
        ObjectKit.deliverPropIgnoreCase(this, szjspellinfo);
    }
}
