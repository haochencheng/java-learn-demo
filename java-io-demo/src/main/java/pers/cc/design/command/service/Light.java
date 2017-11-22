package pers.cc.design.command.service;

/**
 * 灯对象
 * Created by cc on 2017/7/29.
 */
public class Light {

    private String name;   //灯名字
    private String type;   //灯类型
    private String state;   //灯状态

    public Light(String name) {
        this.name = name;
    }

    public void on() {
        this.state = "on";
        System.out.println("light is on");
    }

    public void off() {
        this.state = "off";
        System.out.println("light is off");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
