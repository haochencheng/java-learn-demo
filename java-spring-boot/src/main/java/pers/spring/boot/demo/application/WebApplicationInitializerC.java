package pers.spring.boot.demo.application;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;

/**
 * Created by cc on 2017/10/27
 */
@Component
public class WebApplicationInitializerC implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("WebApplicationInitializerC onStartup 启动！");
	}
}
