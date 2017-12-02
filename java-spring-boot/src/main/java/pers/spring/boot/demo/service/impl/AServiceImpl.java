package pers.spring.boot.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import pers.spring.boot.demo.model.CustomerEvent;
import pers.spring.boot.demo.model.EventInvokeClass;
import pers.spring.boot.demo.model.NotificationEvent;
import pers.spring.boot.demo.service.AService;

/**
 * Created by cc on 2017/9/26
 */
@Service("aService")
public class AServiceImpl implements AService, ApplicationEventPublisherAware, ApplicationContextAware {

	private ApplicationEventPublisher publisher;
	private ApplicationContext applicationContext;

	@Override
	public void monitor(String temperature) {
		// 做一些计算
		System.out.println(this.getClass().getName() + "监控到天气变化：" + temperature);
		List<EventInvokeClass> eventInvokeClasses = new ArrayList<>();
		EventInvokeClass eventInvokeClass = new EventInvokeClass();
		eventInvokeClass.setC(BServiceImpl.class);
		eventInvokeClass.setMethods(new String[] { "getMessage" });
		eventInvokeClasses.add(eventInvokeClass);
		NotificationEvent event = new NotificationEvent(temperature, "1", "事件1", "0", "1", temperature,
				eventInvokeClasses);
		CustomerEvent customerEvent = new CustomerEvent(1, temperature);
		applicationContext.publishEvent(customerEvent);
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
