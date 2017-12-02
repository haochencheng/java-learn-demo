package pers.spring.boot.demo.service.impl;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import pers.spring.boot.demo.model.CustomerEvent;
import pers.spring.boot.demo.service.CService;

/**
 * Created by cc on 2017/9/27
 */
@Service("cService")
public class CServiceImpl implements CService {

	@Override
	@EventListener(condition = "#customerEvent.eventType == 2")
	public void getMessage(CustomerEvent customerEvent) {
		System.out.println(this.getClass().getName() + "接收到消息：天气变化，明日天气为：" + customerEvent.getMessage());
	}

}
