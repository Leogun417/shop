package com.study.shop.util;

import com.study.shop.dao.*;
import com.study.shop.model.DaoInject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by 傲然 on 2017/2/5.
 */
public class DaoUtil {
    public static IDaoFactory createFactory() {
        IDaoFactory factory = null;
        Properties daoProperties = PropertiesUtil.getDaoProperties();
        String factoryName = daoProperties.getProperty("factory");
        try {
            Class<?> factoryClz = Class.forName(factoryName);
            String methodName = "getInstance";
            Method method = factoryClz.getMethod(methodName);
            factory = (IDaoFactory) method.invoke(factoryClz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return factory;
    }

    public static void inject(Object obj) {
        Method[] declaredMethods = obj.getClass().getDeclaredMethods();
        String daoName = null;
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(DaoInject.class)) {
                String value = method.getAnnotation(DaoInject.class).value();
                if (value == null || value.equals("")) {
                    daoName = method.getName().substring(3);
                    daoName = daoName.substring(0,1).toLowerCase() + daoName.substring(1);
                } else {
                    daoName = value;
                }

                Object dao = DaoUtil.createFactory().createDao(daoName);
                try {
                    method.invoke(obj,dao);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
