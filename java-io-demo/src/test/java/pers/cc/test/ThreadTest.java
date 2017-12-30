package pers.cc.test;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA=new Thread(()->{
            System.out.println("A");
        });
        Thread threadB=new Thread(()->{
            System.out.println("B");
        });
        Thread threadC=new Thread(()->{
            System.out.println("C");
        });
        threadA.start();
        threadA.join();
        threadB.start();
        threadB.join();
        threadC.start();
    }

}
