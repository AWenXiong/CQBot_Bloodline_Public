package com.cq.httpapi.demo.myhandler;

public enum Constant {

    Service_Tower("tower", 1, "service"),
    Service_Card("card", 2, "service"),
    Service_remind("remind", 3, "service"),
    Service_Ban("ban", 4, "service"),
    Service_Member_Changed("memberChanged", 5, "service"),

    Sex_Male("男", 1, "sex"),
    Sex_Female("女", 2, "sex"),
    Sex_Unknown("无性别", 3, "sex"),

    Career_Solider("战士", 1, "career"),
    Career_Assassin("刺客", 2, "career"),
    Career_Shooter("射手", 3, "career"),
    Career_Magician("法师", 4, "career"),
    Career_Priest("牧师", 5, "career"),

    Color_Light("光", 1, "color"),
    Color_Dark("暗", 2, "color"),
    Color_Water("水", 3, "color"),
    Color_Fire("火", 4, "color"),
    Color_Wood("树", 5, "color"),
    Color_Thunder("雷", 6, "color"),

    Faction_Alien("异世界", 1, "faction"),
    Faction_Traveler("旅人", 2, "faction"),
    Faction_Magic("法塔", 3, "faction"),
    Faction_Sanctum("圣地", 4, "faction"),
    Faciton_Vampire("异族", 5, "faction"),

    Equipment_Main("主手", 1, "equipment_type"),
    Equipment_Second("副手", 2, "equipment_type"),

    Ext_Pinyin("卡全名全拼", 1, "pinyin_fullname");


    private String constKey;
    private int constValue;
    private String constType;

    private Constant(String key, int value, String type) {
        this.constKey = key;
        this.constValue = value;
        this.constType = type;
    }
}
