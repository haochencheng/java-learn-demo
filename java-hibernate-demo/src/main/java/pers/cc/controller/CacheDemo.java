package pers.cc.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pers.cc.entity.Employee;
import pers.cc.interceptor.EmployeeInterceptor;

public class CacheDemo {

    /**
     * sessionFactory:sessionFactory工厂
     */
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static void main(String[] args) {
        listEmployees();
        listEmployees();
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    /* Method to READ all the employees */
    public static void listEmployees() {
        Session session = sessionFactory.withOptions()
                .interceptor(new EmployeeInterceptor()).openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query<Employee> query = session.createQuery("from Employee");
            query.setCacheable(true);
            List<Employee> employeeList = query.list();
            employeeList.forEach((employee) -> {
                System.out.print("First Name: " + employee.getFristName());
                System.out.print("  Last Name: " + employee.getLastName());
                System.out.println("  Salary: " + employee.getSalary());
            });
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }

}
