package pers.spring.boot.demo.model;

import org.springframework.stereotype.Component;

/**
 * Created by cc on 2017/10/9
 */
@Component
public class CustomerEvent {

	private String eventName;

	private Integer eventType;

	private String message;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public CustomerEvent(String eventName) {
		this.eventName = eventName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public CustomerEvent(Integer eventType, String message) {
		this.eventType = eventType;
		this.message = message;
	}

	public CustomerEvent() {
	}
}
