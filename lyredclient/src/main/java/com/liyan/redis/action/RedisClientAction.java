package com.liyan.redis.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liyan.redis.common.Pagination;
import com.liyan.redis.model.ConnectionInfo;
import com.liyan.redis.model.RedisKeyInfo;
import com.liyan.redis.service.RedisClientService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value = "/redis", produces = "text/plain; charset=UTF-8")
public class RedisClientAction {
	@Autowired
	private RedisClientService  redisClientService; 
	
	
	@RequestMapping("/getDataListPage")
	public String getDataListPage() {
		return "redis_data";
	}
	/**
	 * 分页获取数据库中key
	 * @param db
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/getDataList")
	public @ResponseBody String getDataList(int db,int pageSize,int pageNo) {
		
		
		
		Pagination<RedisKeyInfo> dataListByPage = this.redisClientService.getDataListByPage(db,pageSize,pageNo);
		JSONObject json = new JSONObject();
//		for(RedisKeyInfo info:dataListByPage.getResultList()){
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("key", info.getKey());
//			jsonObject.put("type", info.getType());
//			jsonObject.put("size", info.getSize());
//			jsonObject.put("encoding", info.getEncoding());
//			json.add(jsonObject);
//		}
		json = JSONObject.fromObject(dataListByPage);
		return json.toString();
		
	} 
	
	/**
	 * 获取设置的连接
	 * @param id
	 * @return
	 */
	@RequestMapping("/getConnectionList")
	public @ResponseBody String getConnectionList(String id) {
		List<ConnectionInfo> conns = redisClientService.getConnectionList();
		JSONArray jsonArray = new JSONArray();
		for(int i = 0 ; i<conns.size() ; i++ ){
			ConnectionInfo connectionInfo = conns.get(i);
			JSONObject jsonObject = new JSONObject();
			jsonObject.element("text", connectionInfo.getConnectionName());
			jsonObject.element("children", true);
			jsonObject.element("data", connectionInfo);
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	} 
	/**
	 * 连接数据库
	 * @param ip
	 * @param port
	 * @param auth
	 * @return
	 */
	@RequestMapping("/getDataBase")
    public @ResponseBody String getDataBase(String ip,String port,String auth) {
		try{
			List<String> dataBases = redisClientService.getDataBase(ip,new Integer(port),auth);
			JSONArray jsonArray = new JSONArray();
			for(int i = 0 ; i<dataBases.size() ; i++ ){
				JSONObject connJson = new JSONObject();
				connJson.put("index", i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.element("parent", "#");
				jsonObject.element("data", connJson);
				jsonObject.element("text", dataBases.get(i));
				jsonObject.element("children", false);
				jsonArray.add(jsonObject);
			}
			return jsonArray.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null ; 
		}
		
    }
	/**
	 * 查看当前key详情 
	 * @param key
	 * @return
	 */
	@RequestMapping("/getDetail")
	public @ResponseBody String getDetail(String key){
		try{
			RedisKeyInfo detail = this.redisClientService.getDetail(key);
			JSONObject json = JSONObject.fromObject(detail);
			return json.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null ; 
		}
	}
	/**
	 * 删除key
	 * @param key
	 * @return
	 */
	@RequestMapping("/deleteKey")
	public @ResponseBody String deleteKey(String key){
		this.redisClientService.deleteKey(key);
		return key;
	}
	
}
