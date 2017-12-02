package pers.spring.boot.demo.test.concurrent;

/**
 * Created by cc on 2017/11/13
 */
public class VolatileDemo {

	private static volatile boolean isOver = false;

	public static void main(String[] args) throws InterruptedException {
		new Thread(() -> {
			while (!isOver) {
				System.out.println("程序结束！");
			}
			;
		}).start();
		Thread.sleep(500);
		isOver = true;
	}

}
