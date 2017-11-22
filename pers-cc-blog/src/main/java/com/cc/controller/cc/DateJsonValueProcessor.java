package com.cc.controller.cc;

import java.text.SimpleDateFormat;

import net.sf.json.JSONObject;

/**
 * json-lib 日期处理类
 * 
 * @author Administrator
 *
 */
public class DateJsonValueProcessor {

	private String format;

	public DateJsonValueProcessor(String format) {
		this.format = format;
	}

	public Object processObjectValue(String key, Object value, JSONObject jsonConfig) {
		if (value == null) {
			return "";
		}
		if (value instanceof java.sql.Timestamp) {
			return new SimpleDateFormat(format).format((java.sql.Timestamp) value);
		}
		if (value instanceof java.util.Date) {
			return new SimpleDateFormat(format).format((java.util.Date) value);
		}

		return value.toString();
	}

}
