package com.study.shop.util;

import com.study.shop.model.User;
import com.study.shop.model.Validate;
import com.study.shop.model.ValidateType;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by 傲然 on 2017/2/12.
 */
public class RequestUtil {
    public static Object setParams(Class<?> clz, HttpServletRequest req) {
        Object obj = null;
        try {
            obj = clz.newInstance();
            Map<String, String[]> parameterMap = req.getParameterMap();
            Set<String> keySet = parameterMap.keySet();
            for (String key : keySet) {
                String[] paramters = parameterMap.get(key);
                if (paramters.length > 1) {
                    BeanUtils.copyProperty(obj, key, paramters);
                } else {
                    BeanUtils.copyProperty(obj, key, paramters[0]);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static boolean validate(Class<?> clz, HttpServletRequest req) {
        Field[] declaredFields = clz.getDeclaredFields();
        HashMap<String, String> errors = new HashMap<String, String>();
        boolean isValidate = true;
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Validate.class)) {
                String value = field.getAnnotation(Validate.class).value();
                if (value.equals(ValidateType.NOTNULL)) {
                    if (!checkNULL(field.getName(), req)) {
                        isValidate = false;
                        errors.put(field.getName(), field.getName() + "不能为空！");
                    }
                }
            }
        }
        req.setAttribute("errors", errors);
        return isValidate;
    }

    private static boolean checkNULL(String fieldName, HttpServletRequest req) {
        String parameter = req.getParameter(fieldName);
        if (parameter == null || parameter.equals("")) {
            return false;
        }
        return true;
    }
}
