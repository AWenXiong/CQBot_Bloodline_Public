package com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest;

import java.util.ArrayList;

public class CreateEnemyInfoRequest {

    public String Code;
    public String Name;
    public ArrayList<CreateEnemyInfoRequestLevel> Level;

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

    public ArrayList<CreateEnemyInfoRequestLevel> getLevel() {
        return Level;
    }

    public void setLevel(ArrayList<CreateEnemyInfoRequestLevel> level) {
        Level = level;
    }
}
