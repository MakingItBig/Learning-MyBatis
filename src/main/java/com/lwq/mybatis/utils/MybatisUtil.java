package com.lwq.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName MybatisUtil
 * @Description Util
 * @Author liu wq
 * @Date 2019/3/28 23:52
 * @Version 1.0
 */
public class MybatisUtil {

    private static SqlSessionFactory factory = null;

    static {

        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MybatisUtil() {

    }

    public static SqlSession openSqlSession() {
        return factory.openSession();
    }
}
