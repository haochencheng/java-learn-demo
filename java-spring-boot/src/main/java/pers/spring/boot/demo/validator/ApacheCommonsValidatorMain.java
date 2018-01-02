package pers.spring.boot.demo.validator;

import org.apache.commons.validator.routines.IntegerValidator;

public class ApacheCommonsValidatorMain {

    public static void main(String[] args) {
        IntegerValidator validator=new IntegerValidator();
        Integer value=validator.validate("10");
        System.out.println(value);
        value=validator.validate("A");
        System.out.println(value);
    }



}
