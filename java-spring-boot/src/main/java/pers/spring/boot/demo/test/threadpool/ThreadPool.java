package pers.spring.boot.demo.test.threadpool;

/**
 * Created by cc on 2017/11/30
 */
public interface ThreadPool<Job extends Runnable> {

	// 执行一个job，这个job需要实现Runnable
	void excute(Job job);

	// 关闭线程池
	void shutdown();

	// 减少工作者线程
	void addWorkers(int num);

	// 得到正在等待的执行任务数量
	int getJobSize();

}
