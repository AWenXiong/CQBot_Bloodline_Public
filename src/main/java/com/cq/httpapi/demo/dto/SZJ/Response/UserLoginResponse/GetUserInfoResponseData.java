package com.cq.httpapi.demo.dto.SZJ.Response.UserLoginResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;

public class GetUserInfoResponseData {

    public Long Id;
    public String Code;
    public String Name;
    public String Openid;
    public String Mobile;
    public String Email;
    public Long FreeQueueTime;

    public GetUserInfoResponseData() {

    }

    public GetUserInfoResponseData(Szjuserinfo szjuserinfo) {
        this.Id = szjuserinfo.getId();
        this.Code = szjuserinfo.getCode();
        this.Name = szjuserinfo.getName();
        this.Openid = szjuserinfo.getOpenid();
        this.Mobile = szjuserinfo.getMobile();
        this.Email = szjuserinfo.getEmail();
        this.FreeQueueTime = szjuserinfo.getFreeQueueTime();
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

    public Long getFreeQueueTime() {
        return FreeQueueTime;
    }

    public void setFreeQueueTime(Long freeQueueTime) {
        FreeQueueTime = freeQueueTime;
    }
}
