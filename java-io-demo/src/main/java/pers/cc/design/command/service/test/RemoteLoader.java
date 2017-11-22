package pers.cc.design.command.service.test;

import pers.cc.design.command.service.Light;
import pers.cc.design.command.service.RemoteControlWithUndo;
import pers.cc.design.command.service.impl.LighOffCommand;
import pers.cc.design.command.service.impl.LightOnCommand;

/**
 * Created by cc on 2017/7/29.
 */
public class RemoteLoader {

    public static void main(String[] args) {
        RemoteControlWithUndo remoteControl = new RemoteControlWithUndo();
        Light livingRemoteLight = new Light("liviing Room");
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRemoteLight);
        LighOffCommand livingRoomLightOff = new LighOffCommand(livingRemoteLight);
        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        System.out.println(remoteControl);
        remoteControl.undoButtonWasPushed();
        remoteControl.offButtonWasPushed(0);
        remoteControl.onButtonWasPushed(0);
        System.out.println(remoteControl);
        remoteControl.undoButtonWasPushed();

    }

}
