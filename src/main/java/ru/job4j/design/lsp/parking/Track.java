package ru.job4j.design.lsp.parking;

public class Track implements Car {
    @Override
    public int getSize() {
        return 2;
    }
}
