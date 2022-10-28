package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {
    private final int sizePassengerCar;
    private final int sizeTrack;
    private final List<Car> passengerCars;
    private final List<Car> tracks;

    public SimpleParking(int sizePassengerCar, int sizeTrack) {
        this.sizePassengerCar = sizePassengerCar;
        this.sizeTrack = sizeTrack;
        this.passengerCars = new ArrayList<>(sizePassengerCar);
        this.tracks = new ArrayList<>(sizeTrack);
    }

    @Override
    public boolean addCar(Car car) {
        var rsl = false;
        if (car.getSize() > PassengerCar.SIZE && tracks.size() < sizeTrack) {
            tracks.add(car);
            rsl = true;
        } else if (car.getSize() == PassengerCar.SIZE && passengerCars.size() < sizePassengerCar) {
            passengerCars.add(car);
            rsl = true;
        } else if (passengerCars.size() + car.getSize() <= sizePassengerCar) {
            for (int i = 0; i < car.getSize(); i++) {
                passengerCars.add(car);
            }
            rsl = true;
        }
        return rsl;
    }
}
