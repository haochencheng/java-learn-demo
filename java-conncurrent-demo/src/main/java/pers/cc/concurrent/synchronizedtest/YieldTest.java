package pers.cc.concurrent.synchronizedtest;

public class YieldTest implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        YieldTest run=new YieldTest();
        Thread thread1=new Thread(run,"FirstThread");
        Thread thread2=new Thread(run,"SecondThread");
        thread1.start();
        thread2.start();
    }

}
