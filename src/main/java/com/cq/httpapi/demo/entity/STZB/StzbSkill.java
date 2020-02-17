package com.cq.httpapi.demo.entity.STZB;


public class StzbSkill {

    private Long id;
    private String skillName;
    private String skillDesc;
    private String skillType;
    private String skillSoldierType;
    private String skillDist;
    private String skillTarget;
    private String skillCond;
    private String skillPs;
    private java.sql.Timestamp modifiedOn;
    private String modifiedBy;
    private String modifiedInfo;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }


    public String getSkillDesc() {
        return skillDesc;
    }

    public void setSkillDesc(String skillDesc) {
        this.skillDesc = skillDesc;
    }


    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }


    public String getSkillSoldierType() {
        return skillSoldierType;
    }

    public void setSkillSoldierType(String skillSoldierType) {
        this.skillSoldierType = skillSoldierType;
    }


    public String getSkillDist() {
        return skillDist;
    }

    public void setSkillDist(String skillDist) {
        this.skillDist = skillDist;
    }


    public String getSkillTarget() {
        return skillTarget;
    }

    public void setSkillTarget(String skillTarget) {
        this.skillTarget = skillTarget;
    }


    public String getSkillCond() {
        return skillCond;
    }

    public void setSkillCond(String skillCond) {
        this.skillCond = skillCond;
    }


    public String getSkillPs() {
        return skillPs;
    }

    public void setSkillPs(String skillPs) {
        this.skillPs = skillPs;
    }


    public java.sql.Timestamp getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(java.sql.Timestamp modifiedOn) {
        this.modifiedOn = modifiedOn;
    }


    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }


    public String getModifiedInfo() {
        return modifiedInfo;
    }

    public void setModifiedInfo(String modifiedInfo) {
        this.modifiedInfo = modifiedInfo;
    }

}
