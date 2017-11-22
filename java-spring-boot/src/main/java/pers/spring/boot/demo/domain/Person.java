package pers.spring.boot.demo.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by cc on 2017/11/22.
 */
public class Person {

    @NotNull
    private String name;

    @Min(value = 0)
    @Max(value = 100,message = "人的年龄不能超过200岁")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
