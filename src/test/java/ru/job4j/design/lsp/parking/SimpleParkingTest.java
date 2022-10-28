package ru.job4j.design.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleParkingTest {

    @Test
    void whenAddPassPassengerCars() {
        var first = new PassengerCar();
        var second = new PassengerCar();
        var third = new PassengerCar();
        var parking = new SimpleParking(2, 1);
        parking.addCar(first);
        assertThat(parking.addCar(second)).isTrue();
        assertThat(parking.addCar(third)).isFalse();
    }

    @Test
    void whenAddTracks() {
        var parking = new SimpleParking(3, 1);
        var firstTrack = new Track(4);
        var secondTrack = new Track(2);
        var thirdTrack = new Track(2);
        assertThat(parking.addCar(firstTrack)).isTrue();
        assertThat(parking.addCar(secondTrack)).isTrue();
        assertThat(parking.addCar(thirdTrack)).isFalse();
    }

    @Test
    void whenAddVariousCars() {
        var parking = new SimpleParking(3, 2);
        var firstTrack = new Track(4);
        var secondTrack = new Track(3);
        var thirdTrack = new Track(2);
        var firstPassCar = new PassengerCar();
        var secondPassCar = new PassengerCar();
        assertThat(parking.addCar(firstTrack)).isTrue();
        assertThat(parking.addCar(secondTrack)).isTrue();
        assertThat(parking.addCar(thirdTrack)).isTrue();
        assertThat(parking.addCar(firstPassCar)).isTrue();
        assertThat(parking.addCar(secondPassCar)).isFalse();
    }

    @Test
    void whenIsParkingFullForPassCar() {
        var parking = new SimpleParking(1, 10);
        var firstPassCar = new PassengerCar();
        var secondPassCar = new PassengerCar();
        assertThat(parking.addCar(firstPassCar)).isTrue();
        assertThat(parking.addCar(secondPassCar)).isFalse();
    }

    @Test
    void whenCreateTrackGetException() {
        assertThatThrownBy(() -> new Track(1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}