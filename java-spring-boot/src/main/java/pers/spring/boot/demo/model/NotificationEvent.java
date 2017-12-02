package pers.spring.boot.demo.model;

import java.util.List;

import org.springframework.context.ApplicationEvent;

/**
 * Created by cc on 2017/9/28
 */
public class NotificationEvent extends ApplicationEvent {

	private String id; // 主键
	private String name; // 事件名字
	private String pid; // 事件父id
	private String type; // 事件类型
	private String message; // 消息
	private List<EventInvokeClass> invokeClasses;

	public NotificationEvent(Object source, String id, String name, String pid, String type, String message,
			List<EventInvokeClass> invokeClasses) {
		super(source);
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.type = type;
		this.message = message;
		this.invokeClasses = invokeClasses;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<EventInvokeClass> getInvokeClasses() {
		return invokeClasses;
	}

	public void setInvokeClasses(List<EventInvokeClass> invokeClasses) {
		this.invokeClasses = invokeClasses;
	}
}
