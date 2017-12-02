package pers.spring.boot.demo.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by cc on  2017/11/17
 */
public class GetFaceApi {


    @PostMapping
    public JSONObject getFace(@PathVariable byte[] bytes){
        JSONObject data=new JSONObject();

        return data;
    }


}
