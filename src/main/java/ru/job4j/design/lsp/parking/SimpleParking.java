package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {
    private int sizePassengerCar;
    private int sizeTrack;
    private final List<Car> passengerCars = new ArrayList<>(sizePassengerCar);
    private final List<Car> tracks = new ArrayList<>(sizeTrack);

    public SimpleParking(int sizePassengerCar, int sizeTrack) {
        this.sizePassengerCar = sizePassengerCar;
        this.sizeTrack = sizeTrack;
    }

    @Override
    public boolean addCar(Car car) {
        var rsl = false;
        if (car.getSize() == 2 && tracks.size() < sizeTrack) {
            tracks.add(car);
            rsl = true;
        } else if (passengerCars.size() + 1 < sizePassengerCar && car.getSize() == 2) {
            passengerCars.add(car);
            passengerCars.add(car);
            rsl = true;
        } else if (passengerCars.size() < sizePassengerCar) {
            passengerCars.add(car);
            rsl = true;
        }
        return rsl;
    }
}
