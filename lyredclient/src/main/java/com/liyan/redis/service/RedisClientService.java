package com.liyan.redis.service;

import java.util.List;

import com.liyan.redis.model.ConnectionInfo;

public interface RedisClientService {

	List<String> getDataBase(String ip, int port, String auth) throws Exception;

	List<ConnectionInfo> getConnectionList();
	
}
