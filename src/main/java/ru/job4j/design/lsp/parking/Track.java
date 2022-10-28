package ru.job4j.design.lsp.parking;

public class Track implements Car {

    private final int size;

    public Track(int size) {
        if (size <= PassengerCar.SIZE) {
            throw new IllegalArgumentException();
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
