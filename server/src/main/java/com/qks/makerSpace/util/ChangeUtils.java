package com.qks.makerSpace.util;

import com.qks.makerSpace.entity.database.SpacePerson;

import java.lang.reflect.Field;
import java.util.*;

public class ChangeUtils {

    /**
     * Object转Map
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> getObjectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            String keyName = field.getName();
            Object value = field.get(obj);
            if (value == null)
                value = "";
            map.put(keyName, value);
        }

        return map;
    }

    /**
     * Object转List
     * @param obj
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz)
    {
        List<T> result = new ArrayList<>();
        if(obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    public static  List<Map<String,Object>> objConvertListMap(Object obj) throws IllegalAccessException {
        List<Map<String,Object>> result = new ArrayList<>();
        if (obj instanceof List<?>){
            for (Object o : (List<?>) obj) {
                Map<String,Object> map = new HashMap<>(16);
                Class<?> clazz = o.getClass();
                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);
                    String key = field.getName();
                    Object value = field.get(key);
                    if (value == null){
                        value = "";
                    }
                    map.put(key,value);
                }
                result.add(map);
            }
            return result;
        }
        return null;
    }

    public static List<Map<String, Object>> objToListMap(List<SpacePerson> list) {
        List<Map<String, Object>> list1 = new ArrayList<>();
        Iterator<SpacePerson> iterator = list.iterator();
        while (iterator.hasNext()) {
            int num = 0;
            SpacePerson spacePerson = iterator.next();
            Map<String, Object> map = new HashMap<>();
            map.put("id",String.valueOf(num));
            num++;
            map.put("personName",spacePerson.getPersonName().toString());
            map.put("department",spacePerson.getDepartment().toString());
            map.put("major",spacePerson.getMajor().toString());
            map.put("personPhone",spacePerson.getPersonPhone().toString());
            map.put("personQq",spacePerson.getPersonQq().toString());
            map.put("personWechat",spacePerson.getPersonWechat().toString());
            map.put("note",spacePerson.getNote().toString());
            list1.add(map);
        }
        return list1;
    }


}
