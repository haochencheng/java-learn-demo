package test.java;

import pool.IConnectionPool;
import pool.factory.impl.JedisConnectionPool;
import redis.clients.jedis.Jedis;

import java.util.concurrent.CountDownLatch;

/**
 * Created by cc on 2017/7/29.
 */
public class TestConcurrentConnectionPool {

    //100个线程并发
    private static final int count = 100;

    public static void main(String[] args) {

        final IConnectionPool<Jedis> jedisConnectionPool = new JedisConnectionPool();
        jedisConnectionPool.init(10, 5000);
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 10; j++) {
                    Jedis jedis = jedisConnectionPool.getConnection();
                    System.out.println(jedis.get("cc"));
                    //查询结束释放连接
                    try {
                        jedisConnectionPool.release(jedis);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }

}
