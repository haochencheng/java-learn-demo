package pers.cc.design.adapter.service.impl;

import pers.cc.design.adapter.service.Duck;

/**
 * 绿头鸭
 * Created by cc on 2017/7/29.
 */
public class MallardDuck implements Duck {


    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
}
