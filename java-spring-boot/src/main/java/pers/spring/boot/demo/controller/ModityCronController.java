package pers.spring.boot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pers.spring.boot.demo.test.task.UpdateCronTask;

/**
 * Created by cc on 2017/10/25
 */
@Controller
public class ModityCronController {

	@GetMapping("/cron/{s}")
	public void modify(@PathVariable("s") String s) {
		UpdateCronTask.cron = "0/" + s + " * * * * ?";
	}

}
