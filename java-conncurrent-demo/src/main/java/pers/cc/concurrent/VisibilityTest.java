package pers.cc.concurrent;

//线程可见性问题
public class VisibilityTest {

    private static boolean ready;
    private static int number;


    public static void main(String[] args) {
        new WriterThread().start();
        new ReaderThread().start();
    }

    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!ready){
                System.out.println(ready);
            }
            System.out.println(number);
        }
    }

    private static class WriterThread extends Thread {
        public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            number = 100;
            ready = true;
        }
    }


}
