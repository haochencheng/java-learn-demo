package pers.cc.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 获取mybatis的sessionFactory工具类
 * 
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月19日
 * @Version:1.0.0
 */
public class SqlSessionFactoryUtil {

    private static SqlSessionFactory sqlSessionFactory;

    private static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            InputStream inputStream = null;
            try {
                inputStream = Resources
                        .getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder()
                        .build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession openSession() {
        return getSqlSessionFactory().openSession();
    }
}
