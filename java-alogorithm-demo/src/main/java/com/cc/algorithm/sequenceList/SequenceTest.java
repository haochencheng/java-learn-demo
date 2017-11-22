package com.cc.algorithm.sequenceList;


/**
 * 测试顺序表
 * Created by cc on 2016/12/27.
 */
public class SequenceTest {

    public static void main(String[] args) {
        int j;
        int i;
        int length;
        DATA data=new DATA();
        data.age=12;
        data.key="cc";
        data.name="cc";
        SLType slType=new SLType();
        //插入操作
        System.out.println("----------------插入操作-----------------");
        System.out.println(slType.SLInsert(slType,10,data));
        System.out.println(slType.SLInsert(slType,99,data));
        //添加操作
        System.out.println("----------------添加操作-----------------");
        System.out.println("添加前顺序表长度:"+slType.SLLength(slType));
        System.out.println("是否添加成功:"+slType.SLadd(slType,data));
        System.out.println("添加后顺序表长度"+slType.SLLength(slType));
        //删除操作
        /*System.out.println("----------------删除操作-----------------");
        System.out.println(slType.SLDelete(slType,3));
        System.out.println("删除后顺序表长度"+slType.SLLength(slType));*/
        System.out.println("------------根据顺序号查找操作-------------");
        DATA findData=slType.SLFindByNum(slType,3);
        System.out.println(findData);
        System.out.println("------------根据关键字查找操作-------------");
        System.out.println(slType.SlFindByCont(slType,"4"));
        System.out.println("------------显示所有节点操作-------------");
        slType.SLALL(slType);
    }

}
