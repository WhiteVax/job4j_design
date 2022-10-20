package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public abstract class Parking {
    private final List<Car> passengerCar = new ArrayList<>(20);
    private final List<Car> tracks = new ArrayList<>(10);

    public boolean addCar(Car car) {
        return false;
    }
}
