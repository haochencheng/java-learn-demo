package pers.cc.concurrent;

//https://www.cnblogs.com/paddix/p/5374810.html
//Java 并发编程：核心理论
public class ShareData {

    public static int count=0;

    public static void main(String[] args) {
        //noSynchronized();
        synchronizedData ();
    }


    public  static void noSynchronized(){
            final ShareData data=new ShareData();
            for (int i = 0; i < 10; i++) {
                new Thread(()->{
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 100; j++) {
                        data.addCount();
                    }
                    System.out.println( count+" ");
                }).start();
            }
            System.out.println("count=" + count);
    }

    public  static void synchronizedData(){
        final ShareData data=new ShareData();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 100; j++) {
                    data.addCountSync();
                }
                System.out.println( count+" ");
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count=" + count);
    }


    public void addCount(){
        count++;
    }

    /**
     * 增加 synchronized 关键字
     */
    public synchronized void  addCountSync(){
        count++;
    }

}
