package com.cc.alogorithm.stack;


public class StackDemo {


    public static void main(String[] args) {
        //入栈操作
        Data3 data3=new Data3();
        data3.age=1;
        data3.name="11";
        StackType stackType=new StackType();
        pushST(stackType,data3);
        data3.age=2;
        data3.name="lisi";
        pushST(stackType,data3);
        data3.age=3;
        data3.name="wang5";
        pushST(stackType,data3);
        //出战操作
        System.out.println(popST(stackType));
        System.out.println(popST(stackType));
        System.out.println(popST(stackType));
        System.out.println(popST(stackType));
    }

    static class StackType {
        static  final int MAXLEN=50;
        Data3[] data=new Data3[MAXLEN+1];  //数据元素
        int top;  //栈顶

    }

    static class Data3 {
        String name;
        int age;


        @Override
        public String toString() {
            return "Data3{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


    static  StackType STInit(){
        StackType p;
        if ((p=new StackType())!=null){
            p.top=0;
            return p;
        }
        return null;
    }

    //判断栈是否为空
    static boolean isEmpty(StackType s){
        return s.top==0;
    }

    //判断是否满栈
    static boolean STIsFull(StackType s){
        return s.MAXLEN==s.top;
    }

    //清空栈
    static void  clearST(StackType s){
        s.top=0;
    }

    //释放空间
    static void STFree(StackType s){
        s=null;
    }

    //入栈操作
    static int pushST(StackType s,Data3 data){
        if ((s.top+1)>s.MAXLEN){
            System.out.println("栈溢出！");
            return 0;
        }
        s.data[++s.top]=data;
        return 1;
    }

   static Data3 popST(StackType s){
        if (s.top==0){
            System.out.println("栈为空");
        }
        return (s.data[s.top--]);
   }

   static Data3 peekST(StackType s){
       if (s.top==0){
           System.out.println("栈为空");
       }
       return  s.data[s.top];
   }








}
