package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public abstract class University {
    List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if (student.getAvg() > 11.0) {
            students.add(student);
        }
    }
}
