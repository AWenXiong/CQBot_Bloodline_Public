package com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest;

import java.util.ArrayList;

public class CreateEnemyInfoRequestLevel {

    public Integer Level;
    public Double FightingCapacity;
    public String AdditionType;
    public String AdditionOption;
    public Double AdditionAmmount;
    public ArrayList<CreateEnemyInfoRequestLevelCard> Cards;

    public ArrayList<CreateEnemyInfoRequestLevelCard> getCards() {
        return Cards;
    }

    public void setCards(ArrayList<CreateEnemyInfoRequestLevelCard> cards) {
        Cards = cards;
    }

    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer level) {
        Level = level;
    }

    public Double getFightingCapacity() {
        return FightingCapacity;
    }

    public void setFightingCapacity(Double fightingCapacity) {
        FightingCapacity = fightingCapacity;
    }

    public String getAdditionType() {
        return AdditionType;
    }

    public void setAdditionType(String additionType) {
        AdditionType = additionType;
    }

    public String getAdditionOption() {
        return AdditionOption;
    }

    public void setAdditionOption(String additionOption) {
        AdditionOption = additionOption;
    }

    public Double getAdditionAmmount() {
        return AdditionAmmount;
    }

    public void setAdditionAmmount(Double additionAmmount) {
        AdditionAmmount = additionAmmount;
    }
}
