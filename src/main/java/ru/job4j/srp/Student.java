package ru.job4j.srp;

public class Student {
    private String name;
    private String surname;
    private double avg;
    private boolean increaseScholarship;

    public double getAvg() {
        return avg;
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
