package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Ошибки LCP
 */

public class Class {
    List<Student> students = new ArrayList<>();

    public void printHonorsStudent() {
        for (var student: students) {
            if (student.getAvg() > 8.0) {
                System.out.println(student);
            }
        }
    }

    public List<Student> getHonorsStudent() {
        return students;
    }

    public void add(Student student) {
        students.add(student);
    }
}
