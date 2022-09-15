package ru.job4j.ood.srp;

import java.util.*;

/**
 * Нарушения SRP
 * - нарушение зависимости от абстракции
 * - нарушение единой ответственности
 */

public class Emulator implements EmulatorClass {
    @Override
    public Student findMaxAvg(List<Student> list) {
        var student = list.stream().max(Comparator.comparing(Student::getAvg)).orElse(null);
        if (student != null) {
            student.setIncreaseScholarship(true);
        }
        return student;
    }
}
