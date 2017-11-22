package pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cc on 2017/7/29.
 */
public abstract class ConnectionPool<T> implements IConnectionPool<T> {


    protected AtomicBoolean isClosed = new AtomicBoolean(false);

    private int maxTotal = 10;
    private long maxWait = 5000;    //获取 时长 默认5s

    //atomic
    //volatile
    protected AtomicInteger activeSize = new AtomicInteger(0);

    protected LinkedBlockingQueue<T> idle;
    protected LinkedBlockingQueue<T> busy;

    @Override
    public void init(int maxTotal, int maxWait) {
        this.maxTotal = maxTotal;
        this.maxWait = maxWait;
        idle = new LinkedBlockingQueue<>();
        busy = new LinkedBlockingQueue<>();
    }

    @Override
    public T getConnection() {
        T conn = null;
        //计时
        long now = System.currentTimeMillis();
        while (conn == null) {
            //空闲队列获取
            conn = idle.poll();
            if (conn != null) {
                busy.offer(conn);
                return conn;
            }
            //为空 判断池的大小 如果多个线程进入 先加后减
            if (activeSize.get() < maxTotal) {
                //++i
                //i=i+1 不是原子操作
                if ((activeSize.incrementAndGet()) <= maxTotal) {
                    conn = createConnection();
                    //放入busy队列
                    busy.offer(conn);
                    return conn;
                } else {
                    activeSize.decrementAndGet();
                }
            }
            //时间,前面没有获取到 进入等待状态
            try {
                conn = idle.poll(maxWait - (System.currentTimeMillis() - now), TimeUnit.MILLISECONDS);
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
    public abstract T createConnection();

    @Override
    public abstract void release(T conn) throws Exception;

    @Override
    public abstract void close();

    @Override
    public Integer getTotalConnSize() {
        return activeSize.get();
    }

    @Override
    public Integer getFreeConnSize() {
        return idle.size();
    }
}
