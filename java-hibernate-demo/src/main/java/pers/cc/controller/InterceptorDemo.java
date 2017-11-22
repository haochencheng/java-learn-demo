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

/**
 * 使用拦截器Demo
 * 
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月22日
 * @Version:1.0.0
 */
public class InterceptorDemo {

    /**
     * sessionFactory:sessionFactory工厂
     */
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static void main(String[] args) throws Exception {

        InterceptorDemo me = new InterceptorDemo();

        /* Add few employee records in database */
        Integer empID1 = me.addEmployee("Zara", "Ali", 1000);
        Integer empID2 = me.addEmployee("Daisy", "Das", 5000);

        /* List down all the employees */
        me.listEmployees();

        /* Update employee's records */
        me.updateEmployee(empID1, 8888);

        /* Delete an employee from the database */
        me.deleteEmployee(empID2);

        /* List down new list of the employees */
        me.listEmployees();

        sessionFactory.close();
    }

    /* Method to CREATE an employee in the database */
    public Integer addEmployee(String fristName, String lastName, int salary) {
        Session session = sessionFactory.withOptions()
                .interceptor(new EmployeeInterceptor()).openSession();
        Transaction tx = null;
        Integer id = null;
        try {
            tx = session.beginTransaction();
            Employee employee = new Employee(fristName, lastName, salary);
            id = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return id;
    }

    /* Method to READ all the employees */
    public void listEmployees() {
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

    /* Method to UPDATE salary for an employee */
    public void updateEmployee(Integer EmployeeID, int salary) {
        Session session = sessionFactory.withOptions()
                .interceptor(new EmployeeInterceptor()).openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, EmployeeID);
            employee.setSalary(salary);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an employee from the records */
    public void deleteEmployee(Integer EmployeeID) {
        Session session = sessionFactory.withOptions()
                .interceptor(new EmployeeInterceptor()).openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
