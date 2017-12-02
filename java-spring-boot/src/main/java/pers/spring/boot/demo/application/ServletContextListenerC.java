package pers.spring.boot.demo.application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;

/**
 * Created by cc on 2017/10/27
 */
@Component
public class ServletContextListenerC implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("ServletContextListener contextInitialized 初始化！");
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("ServletContextListener contextDestroyed 销毁！");
	}
}
