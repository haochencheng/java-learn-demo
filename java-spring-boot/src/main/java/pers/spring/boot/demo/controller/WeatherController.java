package pers.spring.boot.demo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pers.spring.boot.demo.service.AService;

/**
 * Created by cc on 2017/9/26
 */
@RestController
public class WeatherController {

	@Resource
	private AService aService;

	@GetMapping("/weather/{temperture}")
	public String revice(@PathVariable("temperture") String temperture) {
		aService.monitor(temperture);
		return "success";
	}

}
