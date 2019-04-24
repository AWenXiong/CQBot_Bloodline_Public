package com.cq.httpapi.demo.kit;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;

public class ObjectKit {

    public static JSONObject getJSONObjectFromFileUsingBufferedInputStream(String filePath) {
        try {
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
            StringBuilder strData = new StringBuilder();
            int n = -1;
            byte[] bytes = new byte[2048];
            while ((n = inputStream.read(bytes, 0, bytes.length)) != -1) {
                String str = new String(bytes, 0, n, "UTF-8");
                strData.append(str);
            }
            JSONObject data = JSONObject.parseObject(strData.toString());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // faster
    public static JSONObject getJSONObjectFromFileUsingBufferedReader(String filePath) {
        try {
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
            StringBuilder strData = new StringBuilder();
            String str = null;
            while ((str = inputStream.readLine()) != null) {
                strData.append(str);
            }
            JSONObject data = JSONObject.parseObject(strData.toString());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 提取对象o的所有属性名、属性类型、属性值
     *
     * @param o
     * @return
     */
    public static JSONObject parseObjectPropToJSONObject(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        JSONObject res = new JSONObject();
        JSONArray prop = new JSONArray();
        for (int i = 0; i < fields.length; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", fields[i].getName());
            jsonObject.put("type", fields[i].getType().toString());
            jsonObject.put("value", TranslateKit.getPropValueByName(fields[i].getName(), o));
            prop.add(jsonObject);
        }
        res.put("prop", prop);

        return res;
    }

    /**
     * 将对象o转换为json对象
     * 键为属性名，值为属性值
     *
     * @param o
     * @return
     */
    public static JSONObject parseObjectToJSONObject(Object o) {
        JSONObject res = new JSONObject();
        Field[] fields = o.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
                Object value = field.get(o);
//                String fieldName = fields[i].getName();
//                // 如果传进来的类的属性和对应的getter的大小写不规范的话会出问题
//                // 这里将属性首字母大写，后面的不变
//                String getter = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
//                Method method = o.getClass().getMethod(getter, new Class[]{});
//                method.setAccessible(true);
//                Object value = method.invoke(o, new Object[] {});
                res.put(fieldName, value);
            }
            return res;
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }

    /**
     * 遍历集合collection，转换为JSONArray
     * className为集合collection<T>的类型T的完整类名
     *
     * @param collection
     * @param className
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    public static JSONArray parseCollectionToJSONArray(Collection collection, String className) {
        try {
            JSONArray res = new JSONArray();
            Iterator iterator = collection.iterator();
            Class c = Class.forName(className);
            Field[] fields = c.getDeclaredFields();
            while (iterator.hasNext()) {
                Object o = iterator.next();
                JSONObject jsonObject = new JSONObject();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object fieldValue = field.get(o);
                    /*TODO*/
                    // 不知道fieldValue是如何放进去的
                    jsonObject.put(fieldName, fieldValue);
                }
                res.add(jsonObject);
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object deepClone(Object o) {
        try {
            // 将对象写入流中
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(o);
            // 将对象从流中取出
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Object res = objectInputStream.readObject();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
