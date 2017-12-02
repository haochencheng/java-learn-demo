package pers.spring.boot.demo.application;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

/**
 * Created by cc on 2017/10/27
 */

public class ContextLoaderListenerC extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		System.out.println("ContextLoaderListenerC 初始化！");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		System.out.println("ContextLoaderListenerC 销毁！");
	}
}
