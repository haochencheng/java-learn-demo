package pers.cc.transfer.connpool.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import pers.cc.transfer.connpool.IConnectionPool;
import pers.cc.transfer.util.LoggerUtil;

public class JdbcConnectionPool implements IConnectionPool<Connection> {

    private String url;
    private String user;
    private String password;

    public JdbcConnectionPool(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private final LoggerUtil log = LoggerUtil
            .getInstance(JdbcConnectionPool.class);
    private AtomicBoolean isClosed = new AtomicBoolean(false);

    private int maxTotal = 10;
    private long maxWait = 5000; // 获取 时长 默认5s

    // atomic
    // volatile
    private AtomicInteger activeSize = new AtomicInteger(0);

    private LinkedBlockingQueue<Connection> idle;
    private LinkedBlockingQueue<Connection> busy;

    @Override
    public void init(int maxTotal, int maxWait) {
        this.maxTotal = maxTotal;
        this.maxWait = maxWait;
        idle = new LinkedBlockingQueue<>();
        busy = new LinkedBlockingQueue<>();
        // 初始化连接数为5
        for (int i = 0; i < 5; i++) {
            idle.add(createConnection());
        }
        log.info("初始化连接数:" + getTotalConnSize());
    }

    @Override
    public Connection getConnection() {
        Connection conn = null;
        // 计时
        long now = System.currentTimeMillis();
        while (conn == null) {
            // 空闲队列获取
            conn = idle.poll();
            if (conn != null) {
                busy.offer(conn);
                return conn;
            }
            // 为空 判断池的大小 如果多个线程进入 先加后减
            if (activeSize.get() < maxTotal) {
                // ++i
                // i=i+1 不是原子操作
                if ((activeSize.incrementAndGet()) <= maxTotal) {
                    conn = createConnection();
                    // 放入busy队列
                    busy.offer(conn);
                    return conn;
                } else {
                    activeSize.decrementAndGet();
                }
            }
            // 时间,前面没有获取到 进入等待状态
            try {
                conn = idle.poll(maxWait - (System.currentTimeMillis() - now),
                        TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (conn == null) {
                if ((System.currentTimeMillis() - now) > maxWait) {
                    throw new RuntimeException("timeout");
                }
            } else {
                busy.offer(conn);
            }
        }
        return conn;
    }

    @Override
    public Integer getTotalConnSize() {
        return activeSize.get();
    }

    @Override
    public Integer getFreeConnSize() {
        return idle.size();
    }

    @Override
    public Connection createConnection() {
        Connection coon = null;
        try {
            coon = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        activeSize.incrementAndGet();
        return coon;
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
            log.info("after close" + activeSize);
        }
    }

}
