package ru.job4j.design.lsp.parking;

public class PassengerCar implements Car {
    @Override
    public int getSize() {
        return 1;
    }
}
