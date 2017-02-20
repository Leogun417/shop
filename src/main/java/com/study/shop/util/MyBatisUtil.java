package com.study.shop.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by 傲然 on 2017/2/3.
 */
public class MyBatisUtil {
    private static SqlSessionFactory factory;

    static {
        try {
            InputStream stream = Resources.getResourceAsStream("mybatis.xml");
            factory = new SqlSessionFactoryBuilder().build(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSession() {
        return factory.openSession();
    }

    public static void closeSession(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }
}
