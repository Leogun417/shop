package com.study.shop.dao;

import com.study.shop.util.PropertiesUtil;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created by 傲然 on 2017/2/6.
 */
public class MySqlDaoFactory implements IDaoFactory {
    private static MySqlDaoFactory factory = new MySqlDaoFactory();
    private static HashMap<String, Object> daoMap = new HashMap<String, Object>();

    private MySqlDaoFactory() {
    }

    public static MySqlDaoFactory getInstance() {
        return factory;
    }

    public Object createDao(String name) {
        Object dao = null;
        Properties daoProperties = PropertiesUtil.getDaoProperties();
        String daoClassName = daoProperties.getProperty(name);
        try {
            if (daoMap.containsKey(name)) {
                return daoMap.get(name);
            }
            Class<?> daoClz = Class.forName(daoClassName);
            dao = daoClz.newInstance();
            daoMap.put(name, dao);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return dao;
    }
}
