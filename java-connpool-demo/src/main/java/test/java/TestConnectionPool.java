package test.java;

import pool.IConnectionPool;
import pool.factory.impl.JedisConnectionPool;
import redis.clients.jedis.Jedis;

/**
 * Created by cc on 2017/7/29.
 */
public class TestConnectionPool {

    //100个线程并发
    private static final int count = 100;

    public static void main(String[] args) throws Exception {

        final IConnectionPool<Jedis> jedisConnectionPool = new JedisConnectionPool();
        jedisConnectionPool.init(10, 5);
        for (int i = 0; i < 10; i++) {
            Jedis jedis = jedisConnectionPool.getConnection();
            System.out.println(jedis.get("cc"));
            jedisConnectionPool.release(jedis);
        }
        jedisConnectionPool.close();
    }

}
