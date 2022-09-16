package ru.job4j.ood.lsp;

public class Student {
    private String name;
    private String surname;
    private double avg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", avg=" + avg
                + '}';
    }
}
