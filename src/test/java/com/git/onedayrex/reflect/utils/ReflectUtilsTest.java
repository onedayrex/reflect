package com.git.onedayrex.reflect.utils;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/12/4.
 */
public class ReflectUtilsTest {
    @Test
    public void getAllField() throws Exception {
        Field[] allField = ReflectUtils.getAllField(UserEntity.class);
        Assert.assertEquals(allField[0].getName(),"username");
        Assert.assertEquals(allField[1].getName(),"password");
    }

    @Test
    public void getAllMethod() throws Exception {
        Method[] allMethod = ReflectUtils.getAllMethod(UserEntity.class);
        for (Method method : allMethod) {
            System.out.println(method.getName());
        }
    }

    @Test
    public void setField() throws Exception {
        UserEntity userEntity = new UserEntity();
        ReflectUtils.setField("123",userEntity,ReflectUtils.getField("username",UserEntity.class));
        Assert.assertEquals(userEntity.getUsername(),"123");
    }

    @Test
    public void isPublic() throws Exception {
        Field field = ReflectUtils.getField("username", UserEntity.class);
        boolean isPublic = ReflectUtils.isPublic(field);
        Assert.assertFalse(isPublic);
    }

    @Test
    public void isPublic1() throws Exception {
        Method method = ReflectUtils.getMethod("getUsername", UserEntity.class,null);
        boolean isPublic = ReflectUtils.isPublic(method);
        Assert.assertTrue(isPublic);
    }

    @Test
    public void invoikeMethod() throws Exception {
        UserEntity userEntity = new UserEntity();
        Field field = ReflectUtils.getField("username", UserEntity.class);
        Method method = ReflectUtils.getMethod("setUsername", UserEntity.class,field.getType());
        ReflectUtils.invokeMethod(method,userEntity,"123");
        Assert.assertEquals(userEntity.getUsername(),"123");
    }

}