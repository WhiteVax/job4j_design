package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.List;

/**
 * Ошибки OCP
 */

public class Zoo {
    private Fish animal;
    List<Fish> list = new ArrayList<>();

    public Fish getAnimal(int id) {
        return list.get(id);
    }
}
