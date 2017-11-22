package com.cc.algorithm.sequenceList;

import java.util.Arrays;

/**
 * 定义顺序表及其操作
 * Created by cc on 2016/12/27.
 */
class DATA{         //节点关键字
    String key;
    String name;
    int age;
}                    //定义节点

class SLType{   //定义顺序结构数组

    private static final int MAXLEN=100; //定义顺序表最大长度

    private DATA[] ListData=new DATA[MAXLEN+1]; //保存顺序表的结构数组
    private int ListLen;    //顺序表已存节点数量

    void SLType(SLType SL){ //初始化顺序表
        SL.ListLen=0;
    }

    int SLLength(SLType SL){
        return (SL.ListLen);    //返回顺序表的元素数量
    }

    /////////////////////插入操作///////////////////////////
    //插入节点,在顺序表L的第i个位置插入一个新的节点,使得其后的节点编号依次加一.表长度加1,插入成功返回1,失败返回0
    int SLInsert(SLType SL,int n,DATA data){
        //基本思想,使用数据前先进行校验
        int i;
        if (SL.ListLen>=MAXLEN){     //顺序表节点数量已超过数量最大值
            System.out.println("顺序表已满,不能插入节点!\n");
            return 0;
        }
        if (n<1||n> MAXLEN){     //插入节点顺序号不正确
            System.out.println("插入元素顺序号错误,不能插入元素!\n");
            return 0;
        }
        for(i= SL.ListLen;i>=n;i--){
            SL.ListData[i+1]= SL.ListData[i];
        }
        SL.ListData[n]=data;  //插入节点
        SL.ListLen++;         //插入顺序表节点数量增加1
        return 1;
    }

    /////////////////////追加操作///////////////////////////
    //追加节点,在顺序表末尾新增一个数据节点,表长度加1.
    int SLadd(SLType SL,DATA data){   //增加元素到顺序表末尾
        if (SL.ListLen>=MAXLEN){    //顺序表已满
            System.out.println("顺序表已满,不能再添加节点了!\n");
            return 0;
        }
        SL.ListData[++SL.ListLen]=data;
        return 1;
    }

    /////////////////////删除操作///////////////////////////
    //与插入操作相反,删除第i个节点,其后的所有结点编号依次减一,表长度减一
    int SLDelete(SLType SL,int n){
        int i;
        if (n<1||n>SL.ListLen+1){
            System.out.println("删除节点序号错误,不能删除节点!");
            return 0;
        }
        for (i=n;i< SL.ListLen;i--){        //将顺序表的数据向前移动
            SL.ListData[i]=SL.ListData[i+1];
        }
        SL.ListLen--;   //顺序元素数量减一
        return 1;   //删除成功返回1
    }

    /////////////////////查找节点///////////////////////////
    //在线性表L中查找值为x的特点,并返回该节点在L中的位置,未找到返回一个错误的标识
    //(1)按照顺序号查找节点
    DATA SLFindByNum(SLType SL,int n){
        if (n<1||n>SL.ListLen+1){
            System.out.println("节点序号错误,不能返回节点!");
            return null;
        }
        return SL.ListData[n];
    }

    //(2)按照关键字查找
    int SlFindByCont(SLType SL,String key){
        int i;
        for(i=1;i<= SL.ListLen;i++){
           try {
               String findKey=SL.ListData[i].key;
               if ((findKey).equals(key)){
                   return i;
               }
           }catch (NullPointerException n){
           }

        }
        return 0;
    }

    //显示所有节点
    void SLALL(SLType SL){
        int i;
        for (i=1;i< SL.ListLen+1;i++){
            try {
                System.out.printf("(%S,%s,%d)\n",SL.ListData[i].key,SL.ListData[i].name,SL.ListData[i].age);
            }catch (NullPointerException n){

            }
        }
    }

    //

    @Override
    public String toString() {
        return "SLType{" +
                "ListData=" + Arrays.toString(ListData) +
                '}';
    }
}