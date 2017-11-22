package pers.cc.crm.util;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cc on 2016/12/8.
 */
public class JedisUtil {

    public static void main(String[] args) {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7379));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        jc.set("foo", "bar");
        String value = jc.get("foo");
        System.out.println(value);
    }

    public static String getString(String str) {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7379));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        return jc.get(str);
    }

    public static String setString(String key,String value) {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7379));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        return jc.set(key,value);
    }

}
