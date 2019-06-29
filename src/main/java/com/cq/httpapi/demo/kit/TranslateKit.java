package com.cq.httpapi.demo.kit;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

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

    /**
     * 按照格式生成汉语拼音
     */
    public static String formatPinYin(String src, HanyuPinyinOutputFormat fmt) {
        char[] hz = src.toCharArray();//该方法的作用是返回一个字符数组，该字符数组中存放了当前字符串中的所有字符
        String[] py;//该数组用来存储

        StringBuilder pys = new StringBuilder(); //存放拼音字符串
        try {
            for (int i = 0; i < hz.length ; i++ ){
                //先判断是否为汉字字符
                if(Character.toString(hz[i]).matches("[\\u4E00-\\u9FA5]+")){
                    //将汉字的几种全拼都存到py数组中
                    py = PinyinHelper.toHanyuPinyinStringArray(hz[i], fmt);
                    //取出改汉字全拼的第一种读音，并存放到字符串pys后
                    pys.append(py[0]);
                }else{
                    //如果不是汉字字符，间接取出字符并连接到 pys 后
                    pys.append(Character.toString(hz[i]));
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e){
            e.printStackTrace();
        }
        return pys.toString();
    }

    /**
     * 生成无音调、v式、小写、普通汉语拼音
     * */
    public static String formatPinYin(String src) {
        HanyuPinyinOutputFormat fmt = new HanyuPinyinOutputFormat();
        fmt.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        fmt.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        fmt.setVCharType(HanyuPinyinVCharType.WITH_V);
        return formatPinYin(src, fmt);
    }
}
