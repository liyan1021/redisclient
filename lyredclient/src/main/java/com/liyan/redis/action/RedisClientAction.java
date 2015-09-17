package com.liyan.redis.action;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liyan.redis.service.RedisClientService;


@Controller
@RequestMapping(value = "/redis", produces = "text/plain; charset=UTF-8")
public class RedisClientAction {
	@RequestMapping("/dataPage")
    public String dataPage() {
		return "redis_data";
    }
	
	@Autowired
	private RedisClientService  redisClientService; 
	
	@RequestMapping("/getConnectionList")
	public @ResponseBody String getConnectionList(String id) {
		System.out.println(id);
		
		return "[{ \"text\" : \"Root 1\", \"children\" : true },{ \"text\" : \"Root 2\", \"children\" : true }]";
	} 
	@RequestMapping("/getDataBase")
    public @ResponseBody String getDataBase(String ip,String port,String auth) {
		List<String> dataBases = redisClientService.getDataBase(ip,port,auth);
		ArrayList<String> jsonList = new ArrayList<String>();
		for(int i = 0 ; i<dataBases.size() ; i++ ){
			String str = "{ \"parent\":\"#\", \"text\" : \"db"+i+"\", \"children\" : false }";
			jsonList.add(str);
		}
		JSONArray jsonArray = new JSONArray();
		
		return "[{ \"parent\":\"#\", \"text\" : \"db0\", \"children\" : false },{\"parent\":\"#\", \"text\" : \"db1\", \"children\" : false }]";
    }
	
	
}
