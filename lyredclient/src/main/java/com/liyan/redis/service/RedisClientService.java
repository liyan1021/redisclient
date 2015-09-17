package com.liyan.redis.service;

import java.util.List;

public interface RedisClientService {

	List<String> getDataBase(String ip, String port, String auth);
	
}
