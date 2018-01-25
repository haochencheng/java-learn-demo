package pers.cc.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 人员实体类
 * 
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月21日
 * @Version:1.0.0
 */
public class Employee {

    private Integer id;
    private String fristName;
    private String lastName;
    private int salary;

    public Employee() {
    }

    public Employee(String fristName, String lastName, int salary) {
        this.fristName = fristName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}
