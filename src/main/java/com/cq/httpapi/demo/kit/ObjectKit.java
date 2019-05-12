package com.cq.httpapi.demo.kit;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
    public static JSONObject parseObjectToJSONObject(Object o) {
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
    public static JSONObject parseObjectToKVJSONObject(Object o) {
        JSONObject res = new JSONObject();
        Field[] fields = o.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
                Object value = field.get(o);
                res.put(fieldName, value);
            }
            return res;
        } catch (Exception e) {
            return null;
        }
    }

    public static void deliverProp(Object to, Object from) throws Exception {
        Class fromClass = from.getClass();
        Class toClass = to.getClass();
        Field[] fromFields = fromClass.getDeclaredFields();
        try {
            for (int i = 0; i < fromFields.length; i++) {
                Field fromField = fromFields[i];
                String fromFieldName = fromField.getName();
                fromField.setAccessible(true);
                Object fromFieldValue = fromField.get(from);
                if (fromFieldValue != null) {
                    Field toFiled = toClass.getDeclaredField(fromFieldName);
                    toFiled.setAccessible(true);
                    toFiled.set(to, fromFieldValue);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static void deliverPropIgnoreCase(Object to, Object from) throws Exception {
        Class fromClass = from.getClass();
        Class toClass = to.getClass();
        Field[] fromFields = fromClass.getDeclaredFields();
        Field[] toFields = toClass.getDeclaredFields();
        try {
            for (int i = 0; i < fromFields.length; i++) {
                Field fromField = fromFields[i];
                String fromFieldName = fromField.getName();
                fromField.setAccessible(true);
                Object fromFieldValue = fromField.get(from);
                if (fromFieldValue != null) {
                    for (int j = 0; j < toFields.length; j++) {
                        String toFieldName = toFields[j].getName().toLowerCase();
                        if (toFieldName.equals(fromFieldName.toLowerCase())) {
                            Field toFiled = toFields[j];
                            toFiled.setAccessible(true);
                            toFiled.set(to, fromFieldValue);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw e;
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

    public static Object generateObject(Object o, Map<Class, Class> map) throws InstantiationException, IllegalAccessException {
        Set<Class> classes = map.keySet();
        for (Class c : classes) {
            if (c.isInstance(o)) {
                Class goalClass = map.get(c);
                return goalClass.newInstance();
            }
        }
        return null;
    }
}
