package pers.cc.design.adapter.service.test;

import pers.cc.design.adapter.service.Duck;
import pers.cc.design.adapter.service.TurkeyAdapter;
import pers.cc.design.adapter.service.impl.MallardDuck;
import pers.cc.design.adapter.service.impl.WildTurkey;

/**
 * 鸭子测试类
 * Created by cc on 2017/7/29.
 */
public class DuckTestDrive {

    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();

        WildTurkey turkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(turkey);
        System.out.println("The Turkey says ...");
        turkey.qobble();
        turkey.fly();

        System.out.println("\nThe Duck says...");
        testDuck(duck);

        System.out.println("\nThe TurkeyAdapter says ...");
        testDuck(turkeyAdapter);

    }

    static void testDuck(Duck duck) {
        duck.fly();
        duck.quack();
    }

}
