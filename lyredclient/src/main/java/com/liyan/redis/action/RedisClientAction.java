package com.liyan.redis.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/redis", produces = "text/plain; charset=UTF-8")
public class RedisClientAction {
	@RequestMapping("/dataPage")
    public String dataPage() {
		return "redis_data";
    }
	@RequestMapping("/getDataBase")
    public @ResponseBody String getDataBase(String id) {
		System.out.println(111);
		 return "[{ \"parent\":\"#\", \"text\" : \"db0\", \"children\" : false },{\"parent\":\"#\", \"text\" : \"db1\", \"children\" : false }]";
    }
	
	@RequestMapping("/getConnectionList")
    public @ResponseBody String getConnectionList(String id) {
		System.out.println(id);
		return "[{ \"text\" : \"Root 1\", \"children\" : true },{ \"text\" : \"Root 2\", \"children\" : true }]";
    } 
	
	
}
