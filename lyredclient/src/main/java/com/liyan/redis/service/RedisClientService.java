package com.liyan.redis.service;

import java.util.List;

import com.liyan.redis.common.Pagination;
import com.liyan.redis.model.ConnectionInfo;
import com.liyan.redis.model.RedisKeyInfo;

public interface RedisClientService {

	List<String> getDataBase(String ip, int port, String auth) throws Exception;

	List<ConnectionInfo> getConnectionList();

	List<RedisKeyInfo> getDataList(int db);

	Pagination<RedisKeyInfo> getDataListByPage(Integer db, int pageSize, int pageNo);

	RedisKeyInfo getDetail(String key);

	void deleteKey(String key);
	
}
