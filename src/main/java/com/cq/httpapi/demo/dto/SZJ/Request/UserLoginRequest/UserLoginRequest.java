package com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest;

public class UserLoginRequest {

    public String Code;
    public String Password;
    public Boolean ChangeOpenid;

    @Override
    public String toString() {
        return "UserLoginRequest{" +
                "Code='" + Code + '\'' +
                ", Password='" + Password + '\'' +
                ", ChangeOpenid=" + ChangeOpenid +
                '}';
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Boolean isChangeOpenid() {
        return ChangeOpenid;
    }

    public void setChangeOpenid(Boolean changeOpenid) {
        ChangeOpenid = changeOpenid;
    }
}
