package com.git.onedayrex.reflect.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by onedayrex on 2016/12/4.
 * 反射工具类
 */
public class ReflectUtils {

    /**
     * 获取所有字段
     * 包括private
     * @param clazz
     * @return
     */
    public static Field[] getAllField(Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        return fields;
    }

    /**
     * 获取所有方法
     * @param clazz
     * @return
     */
    public static Method[] getAllMethod(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        return methods;
    }

    /**
     * 设置字段值
     * @param value 值
     * @param desc 对象
     * @param field 字段
     * @param <T>
     */
    public static <T> void setField(Object value,T desc,Field field){
        if (!isPublic(field)) {
            field.setAccessible(true);
        }
        try {
            field.set(desc,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字段是否是public
     * @param field
     * @return
     */
    public static boolean isPublic(Field field){
        /**此处获取到是一个int数值，需要通过
         * Modifier类去辨别**/
        int modifiers = field.getModifiers();
        return Modifier.isPublic(modifiers);
    }

    /**
     * 方法是否是public
     * @param method
     * @return
     */
    public static boolean isPublic(Method method) {
        int modifiers = method.getModifiers();
        return Modifier.isPublic(modifiers);
    }

    public static <T> void invokeMethod(Method method,T desc,Object... obj){
        if (!isPublic(method)) {
            method.setAccessible(true);
        }
        try {
            method.invoke(desc,obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static Field getField(String fieldName,Class clazz) {
        Field declaredField = null;
        try {
            declaredField = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
        return declaredField;
    }

    public static Method getMethod(String methodName, Class clazz,Class<?> type) {
        Method method = null;
        try {
            if (type == null) {
                method = clazz.getDeclaredMethod(methodName);
            }else {
                method = clazz.getDeclaredMethod(methodName,type);
            }
        } catch (NoSuchMethodException e) {
            return null;
        }
        return method;
    }
}
