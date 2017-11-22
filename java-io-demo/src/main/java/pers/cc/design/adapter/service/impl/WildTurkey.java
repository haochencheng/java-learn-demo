package pers.cc.design.adapter.service.impl;

import pers.cc.design.adapter.service.Turkey;

/**
 * 火鸡
 * Created by cc on 2017/7/29.
 */
public class WildTurkey implements Turkey {


    @Override
    public void qobble() {
        System.out.println("Gobble goisiod");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying short distance");
    }
}
