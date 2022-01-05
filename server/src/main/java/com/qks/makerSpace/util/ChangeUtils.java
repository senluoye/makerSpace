package com.qks.makerSpace.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


}
