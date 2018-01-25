package pers.cc.concurrent.synchronizedtest;

//Synchronized 原理
public class SynchronizedDemo {

    public void method() {
        synchronized (this) {
            System.out.println("Method 1 start");
        }
    }

}
