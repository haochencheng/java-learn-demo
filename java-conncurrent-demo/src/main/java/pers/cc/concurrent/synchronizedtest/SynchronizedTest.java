package pers.cc.concurrent.synchronizedtest;

//https://www.cnblogs.com/paddix/p/5367116.html
//synchronized
public class SynchronizedTest {

    public void method1(){
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }


    public void method2(){
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

    public static void main(String[] args) {

    }

    //1、没有同步的情况
   /* Method 1 start
    Method 1 execute
    Method 2 start
    Method 2 execute
    Method 2 end*/
    public static class NoSync{
        public static void main(String[] args) {
            final SynchronizedTest test=new SynchronizedTest();
            new Thread(()-> test.method1()).start();
            new Thread(()-> test.method2()).start();
        }
    }

    public synchronized void syncMethod1(){
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }


    public synchronized void syncMethod2(){
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

    //2、对普通方法同步：
   /* Method 1 start
    Method 1 execute
    Method 1 end
    Method 2 start
    Method 2 execute
    Method 2 end*/
    public static class Sync{

        public static void main(String[] args) {
            final SynchronizedTest test=new SynchronizedTest();
            new Thread(()-> test.syncMethod1()).start();
            new Thread(()-> test.syncMethod2()).start();
        }
    }


    //3、代码块同步同步：
   /* Method 1 start
    Method 1 execute
    Method 2 start
    Method 1 end
    Method 2 execute
    Method 2 end*/
    public static class SyncBlockCode{

        public static void main(String[] args) {
            final SynchronizedTest test=new SynchronizedTest();
            new Thread(()-> test.syncBlockCodeMethod1()).start();
            new Thread(()-> test.syncBlockCodeMethod2()).start();
        }
    }

    public  void syncBlockCodeMethod1(){
        System.out.println("Method 1 start");
        try {
            synchronized (this){
                System.out.println("Method 1 execute");
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }


    public  void syncBlockCodeMethod2(){
        System.out.println("Method 2 start");
        try {
            synchronized (this){
                System.out.println("Method 2 execute");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }




}
