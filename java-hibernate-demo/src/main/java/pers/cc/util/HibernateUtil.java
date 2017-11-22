package pers.cc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import pers.cc.entity.Employee;

/**
 * hibernate工具类
 * 
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月22日
 * @Version:1.0.0
 */
public class HibernateUtil {

    public static void main(String[] args) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Employee employee = new Employee("1", "1", 2);
        session.save(employee);
        tx.commit();
        session.close();
        sessionFactory.close(); // 关闭会话工厂
    }

    private static SessionFactory sessionFactory;

    static {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure().build();
            sessionFactory = new MetadataSources(registry).buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
        }
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }

}
