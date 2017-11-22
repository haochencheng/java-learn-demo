package pool.factory.impl;

import pool.ConnectionType;
import pool.IConnectionPool;
import pool.factory.IConnectionPoolFactory;

/**
 * Created by cc on 2017/7/30.
 */
public class ConnectionPoolFactory implements IConnectionPoolFactory {

    private static volatile ConnectionPoolFactory connectionFactory;

    private ConnectionPoolFactory() {
    }

    public static IConnectionPool createConnPool(Enum type) {
        if (connectionFactory == null) {
            synchronized (ConnectionPoolFactory.class) {
                if (connectionFactory == null) {
                    connectionFactory = new ConnectionPoolFactory();
                }
            }
        }
        return connectionFactory.getConnPool(type);
    }

    @Override
    public IConnectionPool getConnPool(Enum type) {
        IConnectionPool conn = null;
        if (type == ConnectionType.Connection) {
            conn = new JdbcConnectionPool();
        } else if (type == ConnectionType.Jedis) {
            conn = new JedisConnectionPool();
        }
        return conn;
    }
}
