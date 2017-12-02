package pers.spring.boot.demo.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import pers.spring.boot.demo.model.EventInvokeClass;
import pers.spring.boot.demo.model.NotificationEvent;

/**
 * Created by cc on 2017/9/28
 */
public class EventNotifier implements ApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		if (applicationEvent instanceof NotificationEvent) {
			List<EventInvokeClass> invokeClasses = ((NotificationEvent) applicationEvent).getInvokeClasses();
			invokeClasses.forEach((invokeClass) -> {
				Class<?> c = invokeClass.getC();
				String[] methods = invokeClass.getMethods();
				for (String method : methods) {
					try {
						Method m = c.getMethod(method, String.class);
						m.invoke(c.newInstance(), "1");
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					}
				}
			});

		}
	}
}
