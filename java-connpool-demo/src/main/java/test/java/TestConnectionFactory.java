package test.java;

import pool.ConnectionType;
import pool.IConnectionPool;
import pool.factory.impl.ConnectionPoolFactory;
import redis.clients.jedis.Jedis;

/**
 * Created by cc on 2017/7/30.
 */
public class TestConnectionFactory {
    public static void main(String[] args) {
        Jedis jedis = null;
        IConnectionPool conn = ConnectionPoolFactory.createConnPool(ConnectionType.Jedis);
        conn.init(5, 1000);
        jedis = (Jedis) conn.getConnection();
        System.out.println(jedis.get("cc"));
    }

}
