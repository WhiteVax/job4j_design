package ru.job4j.ood.ocp;

public class Fish {
    private int age;
    private String name;
    private int fins;

    public Fish(int age, String name, int fins) {
        this.age = age;
        this.name = name;
        this.fins = fins;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getFins() {
        return fins;
    }
}
