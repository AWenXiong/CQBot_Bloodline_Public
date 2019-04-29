package com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjcardinfo;
import com.cq.httpapi.demo.kit.ObjectKit;

import java.sql.Timestamp;

public class GetAllCardsInfoResponseData {

    public Long Id;
    public String Code;
    public String Name;
    public String NickName;
    public String Color;
    public String Color2;
    public String Sex;
    public String Occupation;
    public String Camp;
    public String Description;
    public Long DeletionStateCode;
    public Long SortCode;
    public Timestamp CreateOn;
    public String CreateUserId;
    public String CreateBy;
    public Timestamp ModifiedOn;
    public String ModifiedUserId;
    public String ModifiedBy;

    public GetAllCardsInfoResponseData(Szjcardinfo szjcardinfo) throws Exception {
        ObjectKit.deliverPropIgnoreCase(this, szjcardinfo);
    }

}
