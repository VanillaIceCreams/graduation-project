package rest;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisClusterDemo {
	@Test
	public void testJedisCluster() throws Exception {
		//创建一个JedisCluster对象
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.72.131", 6380));
		nodes.add(new HostAndPort("192.168.72.131", 6381));
		nodes.add(new HostAndPort("192.168.72.131", 6382));
		nodes.add(new HostAndPort("192.168.72.131", 6383));
		nodes.add(new HostAndPort("192.168.72.131", 6384));
		nodes.add(new HostAndPort("192.168.72.131", 6385));
		//在nodes中指定每个节点的地址
		//jedisCluster在系统中是单例的。
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("name", "zhangsan");
		jedisCluster.set("value", "100");
		String name = jedisCluster.get("name");
		String value = jedisCluster.get("value");
		System.out.println(name);
		System.out.println(value);
		
		
		//系统关闭时关闭jedisCluster
		jedisCluster.close();
	}

}
