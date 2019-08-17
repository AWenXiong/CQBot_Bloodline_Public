package com.cq.httpapi.demo.dto.SZJ.Response.UserLoginResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.kit.ObjectKit;

public class UserRegisterResponseUserInfo {

    public Long Id;
    public String Code;
    public String Name;
    public String Openid;
    public String Mobile;
    public String Email;
    public String WechatOpenid;

    public UserRegisterResponseUserInfo(Szjuserinfo szjuserinfo) throws Exception {
        ObjectKit.deliverPropIgnoreCase(this, szjuserinfo);
    }

    @Override
    public String toString() {
        return "UserRegisterResponseUserInfo{" +
                "Id='" + Id + '\'' +
                ", Code='" + Code + '\'' +
                ", Name='" + Name + '\'' +
                ", Openid='" + Openid + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
