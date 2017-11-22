package pers.spring.boot.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.spring.boot.demo.domain.Person;

import javax.validation.Valid;

/**
 * Created by cc on 2017/11/22.
 */
@RestController
public class PersonController {

    @PostMapping("/person/save")
    public Person save(@Valid @RequestBody Person person){
        return person;
    }


}
