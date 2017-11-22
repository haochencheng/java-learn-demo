package pool.factory.impl;

import java.util.concurrent.LinkedBlockingQueue;

import pool.ConnectionPool;
import redis.clients.jedis.Jedis;

/**
 * Created by cc on 2017/7/29.
 */
public class JedisConnectionPool extends ConnectionPool<Jedis> {

	@Override
	public Jedis createConnection() {
		System.out.println(activeSize.get());
		return new Jedis("localhost", 6379);
	}

	@Override
	public void release(Jedis jedis) throws Exception {
		if (jedis == null) {
			return;
		}
		if (!jedis.isConnected()) {
			if (!busy.remove(jedis)) {
				throw new Exception("1 release failed...");
			}
			return;
		}
		System.out.println("before release 可用连接数" + idle.size());
		if (busy.remove(jedis)) {
			idle.offer(jedis);
		} else {
			throw new Exception("2 release failed...");
		}
		System.out.println("after release 可用连接数" + idle.size());
	}

	@Override
	public void close() {
		if (isClosed.compareAndSet(false, true)) {
			System.out.println("befor close" + activeSize.get());
			LinkedBlockingQueue<Jedis> pool = idle;
			while (!pool.isEmpty()) {
				Jedis conn = pool.poll();
				conn.disconnect();
				activeSize.decrementAndGet();
				if (pool == idle && pool.isEmpty()) {
					pool = busy;
				}
			}
			System.out.println("after close" + activeSize.get());
		}
	}


}
