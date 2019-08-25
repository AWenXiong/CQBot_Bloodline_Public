package com.cq.httpapi.demo.entity.SZJ;


public class Szjenemylevel {

    private long id;
    private long enemyInfoId;
    private double level;
    private long fightingCapacity;
    private String additionType;
    private String additionOption;
    private double additionAmmount;
    private String description;
    private long sortCode;
    private java.sql.Timestamp createOn;
    private String createUserId;
    private String createBy;
    private java.sql.Timestamp modifiedOn;
    private String modifiedUserId;
    private String modifiedBy;
    private long maxNum;
    private String attackDefense;

    public String getAttackDefense() {
        return attackDefense;
    }

    public void setAttackDefense(String attackDefense) {
        this.attackDefense = attackDefense;
    }

    public long getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(long maxNum) {
        this.maxNum = maxNum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getEnemyInfoId() {
        return enemyInfoId;
    }

    public void setEnemyInfoId(long enemyInfoId) {
        this.enemyInfoId = enemyInfoId;
    }


    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }


    public long getFightingCapacity() {
        return fightingCapacity;
    }

    public void setFightingCapacity(long fightingCapacity) {
        this.fightingCapacity = fightingCapacity;
    }


    public String getAdditionType() {
        return additionType;
    }

    public void setAdditionType(String additionType) {
        this.additionType = additionType;
    }


    public String getAdditionOption() {
        return additionOption;
    }

    public void setAdditionOption(String additionOption) {
        this.additionOption = additionOption;
    }


    public double getAdditionAmmount() {
        return additionAmmount;
    }

    public void setAdditionAmmount(double additionAmmount) {
        this.additionAmmount = additionAmmount;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public long getSortCode() {
        return sortCode;
    }

    public void setSortCode(long sortCode) {
        this.sortCode = sortCode;
    }


    public java.sql.Timestamp getCreateOn() {
        return createOn;
    }

    public void setCreateOn(java.sql.Timestamp createOn) {
        this.createOn = createOn;
    }


    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }


    public java.sql.Timestamp getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(java.sql.Timestamp modifiedOn) {
        this.modifiedOn = modifiedOn;
    }


    public String getModifiedUserId() {
        return modifiedUserId;
    }

    public void setModifiedUserId(String modifiedUserId) {
        this.modifiedUserId = modifiedUserId;
    }


    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
