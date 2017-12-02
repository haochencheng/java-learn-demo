package pers.spring.boot.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by cc on 2017/10/31
 */
@RestController
public class HttpRequestDemoApi {

	@GetMapping("/api/user")
	public JSONObject getUser() {
		JSONObject data = new JSONObject();
		data.put("userName", "张三");
		data.put("age", 12);
		return data;
	}

}
