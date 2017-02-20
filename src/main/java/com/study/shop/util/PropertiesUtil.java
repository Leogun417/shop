package com.study.shop.util;

import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by 傲然 on 2017/2/6.
 */
public class PropertiesUtil {
    private static Properties daoProperties = null;

    public static Properties getDaoProperties() {
        try {
            if (daoProperties == null) {
                daoProperties = new Properties();
            }
            daoProperties.load(Resources.getResourceAsStream("dao.properties"));
            //daoProperties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("dao.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return daoProperties;
    }
}
