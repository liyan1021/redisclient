/**
 * @FileName: ICacheService.java
 * @Package com.sfbest.cache.redis
 * 
 * @author zhangshaobin
 * @created 2015年4月17日 下午2:48:05
 * 
 * Copyright 2011-2015 顺丰优选 版权所有
 */
package com.liyan.redis.component.cache.redis;

import java.util.List;

import redis.clients.jedis.Jedis;


/**
 * <p>TODO</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author liyan
 * @since 1.0
 * @version 1.0
 */
public interface RedisCacheService {
	
	public void addList(String key, List<?> list);
	
	public void addObject(String key,Object object);
	
	public void removeList(String key);
	
	public void removeObject(String ... keys);
	
	public List<Object> getList(String key,int start,int end);

	public List<?> getAllList(String key);
	
	public Object getObejct(String key);
	
	public boolean isExist(String key);
	
	public List<String> getDataBases(String ip, int port, String auth) throws Exception;

}

