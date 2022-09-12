package ru.job4j.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Disabled
public class CinemaTest {

    @Test
    public void whenBuy() {
        var account = new AccountCinema();
        var cinema = new Cinema3D();
        var date = Calendar.getInstance();
        var ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenFind() {
        var cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isNull();
    }

    @Test
    public void whenFindIsNotAvailable() {
        var cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertThat(sessions).isNull();
    }

    @Test
    public void whenInvalidPlace() {
        var account = new AccountCinema();
        var cinema = new Cinema3D();
        var date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, -1, 1, date);
        });
    }

    @Test
    public void whenInvalidDate() {
        var account = new AccountCinema();
        var cinema = new Cinema3D();
        var date = new GregorianCalendar();
        date.set(1990, Calendar.JUNE, 6);
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, 11, 1, date);
        });
    }

    @Test
    public void whenBoughtPlace() {
        var account = new AccountCinema();
        var cinema = new Cinema3D();
        var date = Calendar.getInstance();
        cinema.buy(account, 1, 1, date);
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, 1, 1, date);
        });
    }
}