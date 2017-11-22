package pers.cc.design.command.service.impl;

import pers.cc.design.command.service.Command;
import pers.cc.design.command.service.Light;

/**
 * Created by cc on 2017/7/29.
 */
public class LighOffCommand implements Command {

    private Light light;

    public LighOffCommand(Light light) {
        this.light = light;
    }


    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
