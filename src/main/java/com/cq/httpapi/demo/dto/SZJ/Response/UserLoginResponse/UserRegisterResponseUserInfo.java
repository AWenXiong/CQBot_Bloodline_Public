package com.cq.httpapi.demo.dto.SZJ.Response.UserLoginResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;

public class UserRegisterResponseUserInfo {

    public Long Id;
    public String Code;
    public String Name;
    public String Openid;
    public String Mobile;
    public String Email;

    public UserRegisterResponseUserInfo(Szjuserinfo szjuserinfo) {
        Id = szjuserinfo.getId();
        Code = szjuserinfo.getCode();
        Name = szjuserinfo.getName();
        Openid = szjuserinfo.getOpenid();
        Mobile = szjuserinfo.getMobile();
        Email = szjuserinfo.getEmail();
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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOpenid() {
        return Openid;
    }

    public void setOpenid(String openid) {
        Openid = openid;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
