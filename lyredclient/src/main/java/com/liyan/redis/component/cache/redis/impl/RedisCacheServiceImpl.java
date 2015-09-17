/**
 * @FileName: RedisCacheServiceImpl.java
 * @Package com.sfbest.cache.redis.impl
 * 
 * @author li'yan
 * @created 2015年4月17日 下午2:48:39
 * 
 * Copyright 2011-2015 顺丰优选 版权所有
 */
package com.liyan.redis.component.cache.redis.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.liyan.redis.component.cache.redis.RedisCacheService;
import com.liyan.redis.util.ClassPathProperties;
import com.liyan.redis.util.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * <p>redis缓存服务</p>
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
/**
 * @author liyan
 *
 */
@Component
public class RedisCacheServiceImpl implements RedisCacheService,InitializingBean {

	
	protected Logger logger = Logger.getLogger(getClass().getName());
	
	/**
	 * redis读服务器地址 
	 */
	private String redisServerA ;
	
	/**
	 * redis写服务器地址 
	 */
	private String redisServerB;
	
	/**
	 * redis 服务器端口
	 */
	private int redisPortA ; 
	
	
	/**
	 * redis 服务器端口
	 */
	private int redisPortB ;
	
	
	private JedisPool jedisPoolA ; 
	private JedisPool jedisPoolB ; 
	
	/**
	 * 验证吗
	 */
	private String authStrA = "" ;
	private String authStrB = "" ;
	
	private int timeout =  300 ;
	
	
	@Override
	public void afterPropertiesSet(){
		try{
			logger.debug("应用缓存初始化...");
//			//加载配置文件
//			loadConfig();
//			JedisPool poolA = this.connectRedis(redisServerA, redisPortA,authStrA);
//			JedisPool poolB = this.connectRedis(redisServerB, redisPortB,authStrB);
//	//		this.setJedisA(poolA.getResource());
//	//		this.setJedisB(poolB.getResource());
//			this.setJedisPoolA(poolA);
//			this.setJedisPoolB(poolB);
			logger.debug("缓存初始化成功...");
		}catch(Exception e){
			logger.error("应用缓存初始化失败...",e);
		}
	}


	private void loadConfig() {
		
		ClassPathProperties classPathProperties = new ClassPathProperties("redis.properties");
		
		redisServerA = classPathProperties.getProperty("REDIS.SERVER.A");
		redisServerB = classPathProperties.getProperty("REDIS.SERVER.B");
		
		redisPortA = Integer.parseInt(classPathProperties.getProperty("REDIS.PORT.A"));
		redisPortB = Integer.parseInt(classPathProperties.getProperty("REDIS.PORT.B"));
		
		timeout = Integer.parseInt(classPathProperties.getProperty("REDIS.TIME_OUT"));
		
		authStrA = classPathProperties.getProperty("REDIS.AUTH.A");
		authStrB = classPathProperties.getProperty("REDIS.AUTH.B");
	
	}

	
	/**
	 *  获取redis服务器地址
	 *
	 * @author li'yan
	 * @created 2015年4月17日 下午2:51:21
	 *
	 * @return
	 */
	public String getRedisServerA() {
		return redisServerA;
	}
	public String getRedisServerB() {
		return redisServerB;
	}
	/**
	 * 设置redis 服务器地址
	 *
	 * @author li'yan
	 * @created 2015年4月17日 下午2:52:28
	 *
	 * @param redisServer
	 */
	public void setRedisServerA(String redisServerA) {
		this.redisServerA = redisServerA;
	}
	public void setRedisServerB(String redisServerB) {
		this.redisServerB = redisServerB;
	}
	
	/**
	 * 获取redis服务器端口号
	 *
	 * @author li'yan
	 * @created 2015年4月17日 下午2:52:05
	 *
	 * @return
	 */

	public int getRedisPortA() {
		return redisPortA;
	}
	public int getRedisPortB() {
		return redisPortB;
	}

	/**
	 * 设置redis服务器端口
	 *
	 * @author li'yan
	 * @created 2015年4月17日 下午2:52:50
	 *
	 * @param redisPort
	 */
	public void setRedisPortA(int redisPortA) {
		this.redisPortA = redisPortA;
	}

	public void setRedisPortB(int redisPortB) {
		this.redisPortB = redisPortB;
	}
	
	/**
	 *  连接redis
	 *
	 * @author   li'yan
	 * @created 2015年4月17日 下午3:26:15
	 *
	 * @param redisServer
	 * @param redisPort
	 * @return
	 */
	public JedisPool connectRedis(String redisServer,int redisPort,String auth) throws Exception{
		logger.debug(">>>>>连接redis服务器<<<<"+redisServer+":"+redisPort);
		
		JedisPoolConfig config = new JedisPoolConfig();  
		config.setMaxTotal(1024);
//      config.setMaxActive(1024);    	//如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMinIdle(200);  		
        config.setMaxIdle(1024);  		//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
//        config.setMaxWait(5000);		//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
        return new JedisPool(config, redisServer, redisPort,2000,auth,1);   // 默认查询DB1库，超时时间为0
	}
	/**
	 * 关闭redis连接
	 *
	 * @author li'yan
	 * @created 2015年4月17日 下午3:25:26
	 *
	 */
	public void closeRedis(){
		logger.debug(">>>>>断开redis服务器连接<<<<<");
		this.getJedisPoolA().destroy();
		this.getJedisPoolB().destroy();
	}
	 
	/**
	 * 获取jedis对象
	 *
	 * @author li'yan
	 * @created 2015年4月17日 下午2:57:25
	 *
	 * @return
	 */
	public Jedis getJedisA() {
		return this.getJedisPoolA().getResource();
	}




	public Jedis getJedisB() {
		return this.getJedisPoolB().getResource();
	}

	/**
	 * 获取jedisPool
	 *
	 * @author li'yan
	 * @created 2015年4月17日 下午3:25:48
	 *
	 * @return
	 */
	public JedisPool getJedisPoolA() {
		return jedisPoolA;
	}


	public void setJedisPoolA(JedisPool jedisPoolA) {
		this.jedisPoolA = jedisPoolA;
	}


	public JedisPool getJedisPoolB() {
		return jedisPoolB;
	}


	public void setJedisPoolB(JedisPool jedisPoolB) {
		this.jedisPoolB = jedisPoolB;
	}
	/**
	 * 将列表存入缓存
	 *
	 * @author liyan
	 * @created 2015年4月17日 下午3:59:53
	 * 使用写服务器
	 * @param key
	 * @param list
	 */
	public synchronized void addList(String key, List<?> list) {
		Jedis jedis = this.getJedisB();
		try{
			for(Object entity : list ){
				jedis.lpush(key.getBytes(), SerializeUtil.serialize(entity));
			}
			jedis.expire(key, timeout); 				//设置缓存过期时间
		}finally{
			jedis.close();
		}
	}
	
	/**
	 * 将对象存入缓存
	 *
	 * @author zhangshaobin
	 * @created 2015年4月17日 下午4:03:17
	 * 使用写服务器
	 * @param key
	 * @param object
	 */
	public synchronized void addObject(String key,Object object){
		Jedis jedis = this.getJedisB();
		try{
			jedis.set(key.getBytes(),SerializeUtil.serialize(object));
			jedis.expire(key, timeout);				//设置缓存过期时间
		}finally{
			jedis.close();
		}
	}
	
	/**
	 * 删除缓存列表
	 *
	 * @author liyan
	 * @created 2015年4月17日 下午4:03:30
	 *
	 * @param key
	 */
	public synchronized void removeList(String key){
		Jedis jedis = this.getJedisB();
		try{
			jedis.lrange(key, 0, -1);
		}finally{
			jedis.close();
		}
	}
	/**
	 * 删除缓存对象
	 *
	 * @author liyan
	 * @created 2015年4月17日 下午4:40:24
	 *
	 * @param keys
	 */
	public synchronized void removeObject(String ... keys){
		Jedis jedis = this.getJedisB();
		try{
			jedis.del(keys);
		}finally{
			jedis.close();
		}
	}
	/**
	 * 获取制定下标的集合

	 * 使用读服务器
	 * @author liyan
	 * @created 2015年4月17日 下午4:35:43
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public synchronized List<Object> getList(String key,int start,int end){
		Jedis jedis = this.getJedisA();
		ArrayList<Object> arrayList = new ArrayList<Object>();
		try{
			
			List<byte[]> lrange = jedis.lrange(key.getBytes(), start, end);
			for(byte[] bytes : lrange){
				arrayList.add(SerializeUtil.deserialize(bytes));
			}
		}finally{
			jedis.close();
		}
		return arrayList ;
	}
	/**
	 * 获取所有集合
	 *
	 * @author liyan
	 * @created 2015年4月17日 下午4:36:05
	 *
	 * @param key
	 * @return
	 */
	public synchronized List<?> getAllList(String key){
		return this.getList(key, 0, -1);
	}
	
	/**
	 * 获取对象
	 *
	 * @author liyan
	 * @created 2015年4月17日 下午4:41:24
	 *
	 * @param key
	 * @return
	 */
	public synchronized Object getObejct(String key){
		Object deserialize;
		Jedis jedis = this.getJedisA();
		try{
			byte[] bs = jedis.get(key.getBytes());
			deserialize = SerializeUtil.deserialize(bs);
		}finally{
			jedis.close();
		}
		
		return deserialize;
	}
	/**
	 * 查看key是否存在缓存
	 *
	 * @author liyan
	 * @created 2015年4月17日 下午5:11:46
	 *
	 * @param key
	 * @return
	 */
	public synchronized boolean isExist(String key){
		Boolean exists;
		Jedis jedis = this.getJedisA();
		try{
			 exists = jedis.exists(key);
		}finally{
			 jedis.close();
		}
		return exists;
					
	}
}	
