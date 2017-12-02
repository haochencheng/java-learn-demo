package pers.spring.boot.demo.test.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cc on 2017/11/13
 */
public class ReentrantLockExample {

	private int i = 0;
	ReentrantLock lock = new ReentrantLock();

	public void writer() {
		lock.lock();
		try {
			i++;
		} finally {
			lock.unlock();
		}
	}

	public void reader() {
		lock.lock();
		try {
			int a = i;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {

	}

}
