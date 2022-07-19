package ru.job4j.serialization;

import java.util.Arrays;

public class Person {
    private String name;
    private String surname;
    private int age;
    private boolean education;
    private Contact contact;
    private char[] driverCard;

    public Person(String name, String surname, int age, boolean education, Contact contact, char[] driverCard) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.education = education;
        this.contact = contact;
        this.driverCard = driverCard;
    }

    public Contact getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", age=" + age
                + ", education=" + education
                + ", contact=" + contact
                + ", driverCard=" + Arrays.toString(driverCard)
                + '}';
    }
}
