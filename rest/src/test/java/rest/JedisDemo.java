package rest;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
//单机版
public class JedisDemo {
	@Test
	public void testJedisSingle() throws Exception {
		//创建一个Jedis对象
		Jedis jedis = new Jedis("192.168.72.131", 6380);
		jedis.set("test", "hello jedis");
		String string = jedis.get("test");
		System.out.println(string);
		jedis.close();
	}
	//使用连接池
		@Test
		public void testJedisPool() throws Exception {
			//创建一个连接池对象
			//系统中应该是单例的。
			JedisPool jedisPool = new JedisPool("192.168.72.131", 6379);
			//从连接池中获得一个连接
			Jedis jedis = jedisPool.getResource();
			String result = jedis.get("test");
			System.out.println(result);
			//jedis必须关闭
			jedis.close();
			
			//系统关闭时关闭连接池
			jedisPool.close();
			
		}

}
