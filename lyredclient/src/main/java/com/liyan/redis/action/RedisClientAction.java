package com.liyan.redis.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liyan.redis.model.ConnectionInfo;
import com.liyan.redis.model.RedisKeyInfo;
import com.liyan.redis.service.RedisClientService;


@Controller
@RequestMapping(value = "/redis", produces = "text/plain; charset=UTF-8")
public class RedisClientAction {
	@Autowired
	private RedisClientService  redisClientService; 
	
	
	@RequestMapping("/getDataListPage")
	public String getDataListPage() {
		return "redis_data";
	}
	@RequestMapping("/getDataList")
	public @ResponseBody String getDataList(String db) {
		
		List<RedisKeyInfo> dataList = this.redisClientService.getDataList(new Integer(db));
		JSONArray json = new JSONArray();
		for(RedisKeyInfo info:dataList){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("key", info.getKey());
			jsonObject.put("type", info.getType());
			jsonObject.put("size", info.getSize());
			jsonObject.put("encoding", info.getEncoding());
			json.add(jsonObject);
		}
		return json.toString();
		
	} 
	
	@RequestMapping("/getConnectionList")
	public @ResponseBody String getConnectionList(String id) {
		List<ConnectionInfo> conns = redisClientService.getConnectionList();
		JSONArray jsonArray = new JSONArray();
		for(int i = 0 ; i<conns.size() ; i++ ){
			ConnectionInfo connectionInfo = conns.get(i);
			JSONObject connJson = new JSONObject();
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("text", connectionInfo.getConnectionName());
			map.put("children", true);
			connJson.put("host", connectionInfo.getHost());
			connJson.put("port", connectionInfo.getPort());
			connJson.put("auth", connectionInfo.getAuth());
			map.put("data",connJson);
			JSONObject jsonObject = new JSONObject(map);
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	} 
	@RequestMapping("/getDataBase")
    public @ResponseBody String getDataBase(String ip,String port,String auth) {
		try{
			List<String> dataBases = redisClientService.getDataBase(ip,new Integer(port),auth);
			JSONArray jsonArray = new JSONArray();
			for(int i = 0 ; i<dataBases.size() ; i++ ){
				HashMap<String, Object> map = new HashMap<String,Object>();
				JSONObject connJson = new JSONObject();
				connJson.put("index", i);
				map.put("parent", "#");
				map.put("data", connJson);
				map.put("text", dataBases.get(i));
				map.put("children", false);
				JSONObject jsonObject = new JSONObject(map);
				jsonArray.add(jsonObject);
			}
			return jsonArray.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null ; 
		}
		
    }
	
	
}
