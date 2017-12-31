package com.cc.alogorithm.stack;

public class QueueDemo {



    static final int QUEUELEN=15;

    class DATA{
        String name;
        int age;
    }

    class SQType{
        DATA[] data=new DATA[QUEUELEN]; //队列数组
        int head;  //对头
        int tail;  //队尾
    }
}
