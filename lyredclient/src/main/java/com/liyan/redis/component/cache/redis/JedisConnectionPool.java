package com.liyan.redis.component.cache.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisConnectionPool {
	/**
	 * redis读服务器地址 
	 */
	private String redisHost ;
	
	/**
	 * redis 服务器端口
	 */
	private int redisPort ;
	
	/**
	 * redis
	 */
	private Jedis jedis ; 
	
	/**
	 * redis 连接池类
	 */
	private JedisPool jedisPool ; 
	
	/**
	 * 验证吗
	 */
	private String authStr = "" ;
	
	/**
	 * 默认超时时间
	 */
	private int timeout =  300 ;
	
	
	/** 
	  * 创建连接池 
	  * @param driver 
	  * @param name 
	  * @param URL 
	  * @param user 
	  * @param password 
	  * @param maxConn 
	  */ 
	public JedisConnectionPool(){}
}
