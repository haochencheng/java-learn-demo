package pers.spring.boot.demo.test.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cc on 2017/11/30
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

	public static void main(String[] args) {
		DefaultThreadPool deafultThreadPool = new DefaultThreadPool();
		deafultThreadPool.initializeWokers(2);
	}

	// 线程池最大限制数
	private static final int MAX_WORKER_NUMBERS = 10;

	// 线程默认线程数
	private static final int DEAFULT_WORKER_NUMBERS = 5;

	// 线程池最小数量
	private static final int MIN_WORKER_NUMBERS = 1;

	// 工作列表向里面插入工作
	private final LinkedList<Job> jobs = new LinkedList<>();

	// 工作者列表
	private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());

	// 工作者线程数量
	private int workerNum = DEAFULT_WORKER_NUMBERS;

	// 线程编号生成
	private AtomicInteger threadNum = new AtomicInteger();

	public DefaultThreadPool() {
		initializeWokers(DEAFULT_WORKER_NUMBERS);
	}

	public DefaultThreadPool(int num) {
		workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
		initializeWokers(workerNum);
	}

	// 初始化线程工作者
	private void initializeWokers(int num) {
		for (int i = 0; i < num; i++) {
			Worker worker = new Worker();
			workers.add(worker);
			Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
			thread.start();
			System.out.println(thread.getName() + "start!");
		}
	}

	@Override
	public void excute(Job job) {
		if (job != null) {
			// 添加一个工作，然后进行通知
			synchronized (jobs) {
				jobs.addLast(job);
				jobs.notify();
			}
		}
	}

	@Override
	public void shutdown() {
		for (Worker worker : workers) {
			worker.shutdown();
		}
	}

	@Override
	public void addWorkers(int num) {
		synchronized (jobs) {
			// 限制新增的Worker数量不能超过最大值
			if (num + this.workerNum > MAX_WORKER_NUMBERS) {
				num = MAX_WORKER_NUMBERS - this.workerNum;
			}
			initializeWokers(num);
			this.workerNum += num;
		}
	}

	public void removeWorker(int num) {
		synchronized (jobs) {
			if (num > this.workerNum) {
				throw new IllegalArgumentException("beyond workNum");
			}
			// 按照给定的数量停止Worker
			int count = 0;
			while (count < num) {
				Worker worker = workers.get(count);
				if (workers.remove(worker)) {
					worker.shutdown();
					count++;
				}
				this.workerNum -= count;
			}
		}
	}

	@Override
	public int getJobSize() {
		return jobs.size();
	}

	class Worker implements Runnable {

		// 是否工作
		private volatile boolean running = true;

		@Override
		public void run() {
			while (running) {
				Job job = null;
				synchronized (jobs) {
					// 如果工作列表是空的，就wait
					while (jobs.isEmpty()) {
						try {
							jobs.wait();
						} catch (InterruptedException e) {
							// 感知到外部对WorkerThread的中断操作
							Thread.currentThread().interrupt();
							return;
						}
					}
					// 取出一个Job
					job = jobs.removeFirst();
				}
				if (job != null) {
					try {
						job.run();
					} catch (Exception e) {
						// 忽略job中执行的Exception
					}

				}
			}
		}

		public void shutdown() {
			running = false;
		}
	}
}
