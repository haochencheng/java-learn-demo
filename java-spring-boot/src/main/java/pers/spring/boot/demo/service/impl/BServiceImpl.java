package pers.spring.boot.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import pers.spring.boot.demo.model.CustomerEvent;
import pers.spring.boot.demo.service.BService;

/**
 * Created by cc on 2017/9/26
 */
@Service("bService")
public class BServiceImpl implements BService {

	@Resource
	private ApplicationEventPublisher publisher;

	@Override
	@EventListener(condition = "#customerEvent.eventType == 1")
	public void getMessage(CustomerEvent customerEvent) {
		System.out.println(this.getClass().getName() + "接收到消息:天气变化，明日天气为：" + customerEvent.getMessage());
		CustomerEvent customerEvent1 = new CustomerEvent();
		customerEvent.setEventType(2);
		customerEvent.setMessage("2");
		publisher.publishEvent(customerEvent1);
	}

}
