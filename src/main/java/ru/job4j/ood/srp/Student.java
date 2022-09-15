package ru.job4j.ood.srp;

public class Student {
    private String name;
    private String surname;
    private double avg;
    private boolean increaseScholarship;

    public Student(String name, String surname, double avg, boolean increaseScholarship) {
        this.name = name;
        this.surname = surname;
        this.avg = avg;
        this.increaseScholarship = increaseScholarship;
    }

    public double getAvg() {
        return avg;
    }

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

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public boolean isIncreaseScholarship() {
        return increaseScholarship;
    }

    public void setIncreaseScholarship(boolean increaseScholarship) {
        this.increaseScholarship = increaseScholarship;
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + '}';
    }
}
