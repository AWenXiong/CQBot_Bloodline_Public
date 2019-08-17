package com.cq.httpapi.demo.dto.SZJ.Response.UserLoginResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.kit.ObjectKit;

public class UserLoginResponseData {

    public Long Id;
    public String Code;
    public String Name;
    public String Openid;
    public String Mobile;
    public String Email;
    public String WechatOpenid;

    public UserLoginResponseData(Szjuserinfo szjuserinfo) throws Exception {
        ObjectKit.deliverPropIgnoreCase(this, szjuserinfo);
    }

    @Override
    public String toString() {
        return "UserLoginResponseData{" +
                "Id='" + Id + '\'' +
                ", Code='" + Code + '\'' +
                ", Name='" + Name + '\'' +
                ", Openid='" + Openid + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Email='" + Email + '\'' +
                '}';
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

    public void setOpenid(String openid) {
        Openid = openid;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
