package pers.spring.boot.demo.test.threadpool;

/**
 * Created by cc on 2017/11/30
 */
public class TestPool {

	public static void main(String[] args) {
		DefaultThreadPool defaultThreadPool = new DefaultThreadPool(2);
		for (int i = 0; i < 10; i++) {
			int finalI = i;
			defaultThreadPool.excute(new Thread(() -> {
				System.out.println("task-" + finalI + "  is finished!");
			}, i + ""));
		}
	}

}
