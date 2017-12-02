package pers.spring.boot.demo.test.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by cc on 2017/11/15
 */
public class WaitNofty {

	static boolean flag = true;
	static Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		Thread waitThread = new Thread(new Wait(), "waitThread");
		waitThread.start();
		TimeUnit.SECONDS.sleep(1);
		Thread notifyThread = new Thread(new Notify(), "notifyThread");
		notifyThread.start();
	}

	static class Wait implements Runnable {

		@Override
		public void run() {
			synchronized (lock) {
				while (flag) {
					System.out.println(Thread.currentThread() + "flag is true .wa@" + getNow());
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread() + "flag is false .running@" + getNow());
			}

		}
	}

	static class Notify implements Runnable {

		@Override
		public void run() {
			synchronized (lock) {
				System.out.println(Thread.currentThread() + "hold lock . notufy @ " + getNow());
				lock.notifyAll();
				flag = false;
				sleep(5);
			}
			synchronized (lock) {
				System.out.println(Thread.currentThread() + "hold lock again . sleep @ " + getNow());
				sleep(5);
			}
		}
	}

	static String getNow() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	static void sleep(int i) {
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
