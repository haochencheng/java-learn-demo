package pers.cc.design.command.service.impl;

import pers.cc.design.command.service.Command;
import pers.cc.design.command.service.Light;

/**
 * Created by cc on 2017/7/29.
 */
public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
