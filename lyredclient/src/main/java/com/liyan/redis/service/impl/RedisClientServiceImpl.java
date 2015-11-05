package com.liyan.redis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyan.redis.common.Pagination;
import com.liyan.redis.component.cache.redis.RedisCacheService;
import com.liyan.redis.model.ConnectionInfo;
import com.liyan.redis.model.RedisKeyInfo;
import com.liyan.redis.service.RedisClientService;

@Service(value="redisClientService")
public class RedisClientServiceImpl implements RedisClientService{
	
	@Autowired
	private RedisCacheService redisCacheService ; 
	@Override
	public List<String> getDataBase(String ip, int port, String auth) throws Exception {
		//获取数据库列表
		List<String> dataBases = redisCacheService.getDataBases(ip,port,auth);
		ArrayList<String> list = new ArrayList<String>();
		int dataBaseSize = new Integer(dataBases.get(1));
		for(int i = 0 ; i< dataBaseSize ; i++ ){
			list.add("db"+i);
		}
		return list ; 
	}
	@Override
	public List<ConnectionInfo> getConnectionList() {
		//获取数据库连接
		ArrayList<ConnectionInfo> list = new ArrayList<ConnectionInfo>();
		ConnectionInfo connectionInfo = new ConnectionInfo();
		connectionInfo.setConnectionName("连接1");
		list.add(connectionInfo);
		return list;
	}
	@Override
	public List<RedisKeyInfo> getDataList(int db) {
		//获取所有key
		Set<String> allKey = this.redisCacheService.getAllKey(db);
		ArrayList<RedisKeyInfo> keyList = new ArrayList<RedisKeyInfo>();
		for(String key : allKey){
			RedisKeyInfo redisKeyInfo = new RedisKeyInfo();
			redisKeyInfo.setKey(key);
			redisKeyInfo.setType(this.redisCacheService.getKeyType(key));
			redisKeyInfo.setEncoding(this.redisCacheService.getKeyEncoding(key));
			redisKeyInfo.setSize(1);
			keyList.add(redisKeyInfo);
		}
		return keyList ; 
	}
	@Override
	public Pagination<RedisKeyInfo> getDataListByPage(Integer db, int pageSize, int pageNo) {
		Pagination<RedisKeyInfo> pagination = new Pagination<RedisKeyInfo>();
		//获取所有key
		Set<String> allKey = this.redisCacheService.getAllKey(db);
		ArrayList<String> list = new ArrayList<String>(allKey);
		
		pagination.setPageNo(pageNo);
		pagination.setPageSize(pageSize);
		pagination.setRecordCount(list.size());
		List<String> subList ; 
		if(list.size() <= pagination.getToIndex()){
			//截止到总的数据条数(当前数据不足一页，按一页显示)，这样才不会出现数组越界异常
			subList = list.subList(pagination.getFromIndex(),pagination.getRecordCount()); 
		}else{
			subList = list.subList(pagination.getFromIndex(),pagination.getToIndex()); 
		}
		ArrayList<RedisKeyInfo> keyList = new ArrayList<RedisKeyInfo>();
		for(String key : subList){
			RedisKeyInfo redisKeyInfo = new RedisKeyInfo();
			redisKeyInfo.setKey(key);
			redisKeyInfo.setType(this.redisCacheService.getKeyType(key));
			redisKeyInfo.setEncoding(this.redisCacheService.getKeyEncoding(key));
			redisKeyInfo.setSize(1);
			keyList.add(redisKeyInfo);
		}
		pagination.setResultList(keyList);
		return pagination ; 
	}
	@Override
	public RedisKeyInfo getDetail(String key) {
		
		RedisKeyInfo redisKeyInfo = new RedisKeyInfo();
		redisKeyInfo.setKey(key);
		redisKeyInfo.setType(this.redisCacheService.getKeyType(key));
		redisKeyInfo.setEncoding(this.redisCacheService.getKeyEncoding(key));
		redisKeyInfo.setTtl(this.redisCacheService.ttl(key));
		if(redisKeyInfo.getType().equals("string")){
			String obejct = this.redisCacheService.getObejctStr(key);
			redisKeyInfo.setValue(obejct);
		}else{
			Object obejct = this.redisCacheService.getObejct(key);
			redisKeyInfo.setValue((String)obejct);
		}
		return redisKeyInfo;
	}
	@Override
	public void deleteKey(String key) {
		this.redisCacheService.removeObject(key);
	}
	
}
