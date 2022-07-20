package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String surname;

    @XmlAttribute
    private int age;

    @XmlAttribute
    private boolean education;

    private Contact contact;

    @XmlAttribute
    private char[] driverCard;

    public Person(String name, String surname, int age, boolean education, Contact contact, char[] driverCard) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.education = education;
        this.contact = contact;
        this.driverCard = driverCard;
    }

    public Person() {
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
