package com.example.listview_recyclerviewexample;

public class Person {

    private String name;
    private String eyeColor;
    private int age;

    public Person(String name, String eyeColor, int age) {
        this.name = name;
        this.eyeColor = eyeColor;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
