package pool.factory.impl;

import pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by cc on 2017/7/29.
 */
public class JdbcConnectionPool extends ConnectionPool<Connection> {

    @Override
    public Connection createConnection() {
        System.out.println(activeSize.get());
        return null;
    }

    @Override
    public void release(Connection conn) throws Exception {
        if (conn == null) {
            return;
        }
        if (conn.isClosed()) {
            if (!busy.remove(conn)) {
                throw new Exception("1 release failed...");
            }
            return;
        }
        if (busy.remove(conn)) {
            idle.offer(conn);
        } else {
            throw new Exception("2 release failed...");
        }
    }

    @Override
    public void close() {
        if (isClosed.compareAndSet(false, true)) {
            System.out.println("befor close" + activeSize);
            LinkedBlockingQueue<Connection> pool = idle;
            while (!pool.isEmpty()) {
                Connection conn = pool.poll();
                try {
                    conn.close();
                    activeSize.decrementAndGet();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (pool == idle && pool.isEmpty()) {
                    pool = busy;
                }
            }
            System.out.println("after close" + activeSize);
        }
    }
}
