package com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest;

import java.util.ArrayList;

public class CreateEnemyInfoRequest {

    public ArrayList<CreateEnemyInfoRequestLevel> Level;

    public ArrayList<CreateEnemyInfoRequestLevel> getLevel() {
        return Level;
    }

    public void setLevel(ArrayList<CreateEnemyInfoRequestLevel> level) {
        Level = level;
    }
}
