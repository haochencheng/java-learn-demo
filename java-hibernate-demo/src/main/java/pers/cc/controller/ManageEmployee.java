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
 * Employee实体控制层
 * 
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月21日
 * @Version:1.0.0
 */
public class ManageEmployee {

    /**
     * sessionFactory:sessionFactory工厂
     */
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static void main(String[] args) throws Exception {
        int i = 0;
        ManageEmployee me = new ManageEmployee();

        /* Add few employee records in database */
        Integer empID1 = me.addEmployee("Zara", "Ali", 1000);
        Integer empID2 = me.addEmployee("Daisy", "Das", 5000);
        System.out.println("=============第1次执行==============");

        /* Update employee's records */
        me.updateEmployee(empID1, 8888);

        /* Delete an employee from the database */
        me.deleteEmployee(empID2);

        /* List down new list of the employees */
        me.listEmployees();
        System.out.println("=============第2次执行==============");
        Integer empID3 = me.addEmployee("Zara1", "Ali1", 1000);
        me.addEmployee("Daisy1", "Das1", 5000);

        /* Update employee's records */
        me.updateEmployee(empID3, 8888);

        /* List down new list of the employees */
        me.listEmployees();
        me.listEmployees();
        sessionFactory.close();
    }

    /* Method to CREATE an employee in the database */
    public Integer addEmployee(String fristName, String lastName, int salary) {
        System.out.println("=================add");
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
        System.out.println("=================update");
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
        System.out.println("=================delete");
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
