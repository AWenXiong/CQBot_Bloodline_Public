package com.cq.httpapi.demo.dto.SZJ.Response.UserLoginResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.kit.ObjectKit;

public class GetUserInfoResponseData {

    public Long Id;
    public String Code;
    public String Name;
    public String Openid;
    public String Mobile;
    public String Email;
    public Long FreeQueueTime;
    public String WechatOpenid;

    public GetUserInfoResponseData(Szjuserinfo szjuserinfo) throws Exception {
        ObjectKit.deliverPropIgnoreCase(this, szjuserinfo);
    }

}
