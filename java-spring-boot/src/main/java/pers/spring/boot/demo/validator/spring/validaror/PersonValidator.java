package pers.spring.boot.demo.validator.spring.validaror;


import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pers.spring.boot.demo.domain.Person;

public class PersonValidator implements Validator{


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person=(Person) target;
        String name=person.getName();
        if (StringUtils.hasLength(name)){
            errors.reject("personName is not null","人的姓名不能为空！");
        }
    }
}
