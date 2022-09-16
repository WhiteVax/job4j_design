package ru.job4j.ood.lsp;

import java.util.List;

public class ClassMath extends Class {

    @Override
    public void printHonorsStudent() {
        for (var student: students) {
            if (student.getAvg() > 10.0) {
                System.out.println(student);
            }
        }
    }

    @Override
    public List<Student> getHonorsStudent() {
        return students;
    }
}
