//package com.scistor.develop.util.authentication;
//
//
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * Redis工具类
// * @author wzx
// *
// */
//public class JedisPoolUtils {
//
//	private static Logger logger = Logger.getLogger(JedisPoolUtils.class);
//
//	private static JedisPool pool = null;
//
///*	@Value("${redis.maxIdle}")
//	private static Integer redis_maxIdle;
//	@Value("${redis.minIdle}")
//	private static Integer redis_minIdle;
//	@Value("${redis.maxTotal}")
//	private static Integer redis_maxTotal;
//	@Value("${redis.host}")
//	private static String redis_hostname;
//	@Value("${redis.port}")
//	private static Integer redis_port;
//	@Value("${redis.password}")
//	private static String redis_password;*/
///*
//
//	private static Integer redis_maxIdle= jPConfig.getMaxIdle();
//	private static Integer redis_minIdle= jPConfig.getMinIdle();
//	private static Integer redis_maxTotal= jPConfig.getMaxTotal();
//	private static String redis_hostname= jPConfig.getHost();
//	private static Integer redis_port= jPConfig.getPort();
//	private static String redis_password= jPConfig.getPassword();
//*/
//
//	private static Integer redis_maxIdle= 100;
//	private static Integer redis_minIdle= 50;
//	private static Integer redis_maxTotal= 1000;
//	private static String redis_hostname= "10.130.21.20";
//	private static Integer redis_port= 30001;
//	private static String redis_password= "scistorX200@test";
//
//
//	static {
//        //获得池子对象
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxIdle(redis_maxIdle);//最大闲置个数
//        poolConfig.setMinIdle(redis_minIdle);//最小闲置个数
//        poolConfig.setMaxTotal(redis_maxTotal);//最大连接数
////        jedis = new Jedis(pro.getProperty("redis_hostname"), Integer.parseInt(pro.get("redis_port").toString()));
////        jedis.auth(pro.getProperty("redis_password"));
//        pool = new JedisPool(poolConfig, redis_hostname, redis_port);
//    }
//
//    /**
//     * 获得jedis资源的方法
//     * @return
//     */
//    public static Jedis getJedis() {
//
//    	Jedis jedis = null;
//    	try {
//	    	jedis = pool.getResource();
//	    	if(!StringUtils.isEmpty(redis_password))
//	    		jedis.auth(redis_password);
//    	} catch (Exception e) {
//    		logger.error("获取Jedis连接异常，异常信息：" + e.getMessage());
//    		e.printStackTrace();
//    	}
//    	return jedis;
//    }
//
//    /**
//     * 往Redis中设置键值
//     *
//     * @param jedis - jedis连接对象
//     * @param key - 键
//     * @param value - 值
//     */
//    public static void setPool(Jedis jedis,String key,String value){
//    	try {
//    		jedis.set(key,value);
//    	} catch(Exception e) {
//    		logger.error("向redis中写入数据异常，key：" + key + "，异常信息：" + e.getMessage());
//    		e.printStackTrace();
//    	}
//    }
//
//	/**
//	 * 添加有过期时间的Key
//	 * @param jedis
//	 * @param key
//	 * @param secods - key过期时间，单位：秒
//	 * @param value
//	 */
//	public static void setPool(Jedis jedis,String key,int secods, String value){
//		try {
//			jedis.setex(key, secods, value);
//		} catch(Exception e) {
//			logger.error("向redis中写入数据异常，key：" + key + "，异常信息：" + e.getMessage());
//			e.printStackTrace();
//		}
//	}
//
//
//    /**
//     * 从Redis中取值
//     *
//     * @param jedis - jedis连接对象
//     * @param key - 要取值的键
//     * @return
//     */
//    public static String getPool(Jedis jedis,String key){
//    	String value = "";
//    	try {
//    		value = jedis.get(key);
//    	} catch(Exception e) {
//    		logger.error("从redis中获取数据异常，key：" + key + "，异常信息：" + e.getMessage());
//    		e.printStackTrace();
//    	}
//    	return value;
//    }
//    /**
//     * 从Redis中删除指定的键值
//     *
//     * @param jedis - jedis连接对象
//     * @param key - 要删除的键
//     */
//    public static void delKey(Jedis jedis,String key) {
//    	try {
//    		jedis.del(key);
//    	} catch(Exception e) {
//    		logger.error("从redis中删除数据异常，key：" + key + "，异常信息：" + e.getMessage());
//    		e.printStackTrace();
//    	}
//    }
//
//    /**
//     * 关闭连接
//     * @param jedis - jedis连接对象
//     */
//    public static void close(Jedis jedis){
//    	try {
//	        if(jedis != null){
//	        	jedis.close();
//	        	jedis = null;
//	        }
//    	} catch(Exception e) {
//    		//关闭连接异常多数是因为关闭了重复的连接（已关闭的连接）导致，此处不再打印输出
//    	}
//    }
//
//
//
//
//    public static void main(String[] args) {
//
//    	Jedis jedis = getJedis();
//        String str = getPool(jedis,"key");
//        System.out.println(redis_maxIdle);
//        setPool(jedis,"key","qqqqqqq");
//        str = getPool(jedis,"key");
//        System.out.println(str);
//        delKey(jedis,"key");
//        str = getPool(jedis,"key");
//        System.out.println(str);
//    }
//}
