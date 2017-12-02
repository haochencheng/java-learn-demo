package pers.spring.boot.demo.model;

/**
 * Created by cc on 2017/9/28
 */
public class EventInvokeClass {

	private Class<?> c; // 接收方类
	private String[] methods; // 接收方方法

	public EventInvokeClass() {

	}

	public EventInvokeClass(Class c, String[] methods) {
		this.c = c;
		this.methods = methods;
	}

	public String[] getMethods() {
		return methods;
	}

	public void setMethods(String[] methods) {
		this.methods = methods;
	}

	public Class getC() {
		return c;
	}

	public void setC(Class c) {
		this.c = c;
	}
}
