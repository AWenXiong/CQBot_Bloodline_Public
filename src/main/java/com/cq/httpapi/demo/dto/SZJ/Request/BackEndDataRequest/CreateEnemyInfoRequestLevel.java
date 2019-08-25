package com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest;

import java.util.ArrayList;

public class CreateEnemyInfoRequestLevel {

    public Double Level;
    public Long FightingCapacity;
    public String AdditionType;
    public String AdditionOption;
    public Double AdditionAmmount;
    public Long MaxNum;
    public String AttackDefense;
    public ArrayList<CreateEnemyInfoRequestLevelCard> Cards;

    public String getAttackDefense() {
        return AttackDefense;
    }

    public void setAttackDefense(String attackDefense) {
        AttackDefense = attackDefense;
    }

    public Long getMaxNum() {

        return MaxNum;
    }

    public void setMaxNum(Long maxNum) {
        MaxNum = maxNum;
    }

    public ArrayList<CreateEnemyInfoRequestLevelCard> getCards() {
        return Cards;
    }

    public void setCards(ArrayList<CreateEnemyInfoRequestLevelCard> cards) {
        Cards = cards;
    }

    public Double getLevel() {
        return Level;
    }

    public void setLevel(Double level) {
        Level = level;
    }

    public Long getFightingCapacity() {
        return FightingCapacity;
    }

    public void setFightingCapacity(Long fightingCapacity) {
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
