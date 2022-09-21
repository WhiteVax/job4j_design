package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public class UniversityMath extends University {
    List<Student> list = new ArrayList<>();

    @Override
    public void addStudent(Student student) {
        if (student.getAvg() > 12.0) {
            students.add(student);
        }
    }
}
