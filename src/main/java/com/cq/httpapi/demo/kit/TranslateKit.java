package com.cq.httpapi.demo.kit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TranslateKit {

    private static HashMap<String, String> purchaseServiceMap = new HashMap<>();
    private static HashMap<String, String> cardCareerMap = new HashMap<>();

    private TranslateKit() {
        purchaseServiceMap.put("tower", "问答功能");
        purchaseServiceMap.put("card", "血族卡牌信息查询");
        purchaseServiceMap.put("remind", "定时提醒");
        purchaseServiceMap.put("ban", "群内禁言");
        purchaseServiceMap.put("memberChanged", "群成员变更");

        cardCareerMap.put("1", "战士");
        cardCareerMap.put("2", "刺客");
        cardCareerMap.put("3", "射手");
        cardCareerMap.put("4", "法师");
        cardCareerMap.put("5", "牧师");
    }

    /**
     * 将属性转换为
     */
    public static String TranslateProp(String table, String key, String value) {

        try {
            String mapName = formalize(table, key) + "Map";
            Class clazz = Class.forName("com.cq.httpapi.demo.kit.TranslateKit");
            Field mapField = clazz.getDeclaredField(mapName);
            mapField.setAccessible(true);
            TranslateKit instance = (TranslateKit) clazz.newInstance();
            Map hashMap = (HashMap) mapField.get(instance);
            return (String) hashMap.get(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getPropValueByName(String fieldName, Object o) {
        try {
            String getter = formalize("get", fieldName);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            method.setAccessible(true);
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    /**
     * 将s1 , s2 拼接起来， s1全为小写，s2首字母大写，其余字母全部小写
     */
    private static String formalize(String s1, String s2) {
        return s1.toLowerCase() + s2.substring(0, 1).toUpperCase() + s2.substring(1).toLowerCase();
    }
}
