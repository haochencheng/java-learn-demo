package pers.cc.annotation;

public class Apple {

    public static void main(String[] args) {
        Apple apple=new Apple();
        apple.displayName();
    }

    @FruitName("Apple")
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String color;

    public String getAppleName() {
        return appleName;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void displayName(){
        System.out.println("水果的名字："+appleName+"水果的颜色"+color);
    }
}
