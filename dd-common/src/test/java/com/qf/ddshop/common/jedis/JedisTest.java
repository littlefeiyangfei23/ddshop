package com.qf.ddshop.common.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

public class JedisTest {

    @Test
    public void testJedis01(){
        Jedis jedis=new Jedis("10.31.161.48",6379);
        jedis.set("name","wyf");
        System.out.println(jedis.get("name"));
        jedis.close();
    }

    @Test
    public void testJedis02(){
        JedisPool pool=new JedisPool("10.31.161.48",6379);
        Jedis jedis=pool.getResource();
        jedis .set("name","baba");
        System.out.println(jedis.get("name"));
        jedis.close();
        pool.close();
    }

    @Test
    public  void testJedis03(){
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("10.31.161.48",9001));
        nodes.add(new HostAndPort("10.31.161.48",9002));
        nodes.add(new HostAndPort("10.31.161.48",9003));
        nodes.add(new HostAndPort("10.31.161.48",9004));
        nodes.add(new HostAndPort("10.31.161.48",9005));
        nodes.add(new HostAndPort("10.31.161.48",9006));

        JedisCluster jedisCluster = new JedisCluster(nodes);

        jedisCluster.set("name","哥哥");
        System.out.println(jedisCluster.get("name"));
        jedisCluster.close();
    }
}
