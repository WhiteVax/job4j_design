package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled
public class GenerateTest {

    @Test
    public void whenCorrect() {
        var generate = new Generate();
        var temple = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("name", "Petr",
                "subject", "Arsentev");
        assertThat(generate.produce(temple, map)).isEqualTo("I am a Petr Arsentev, Who are you? ");
    }

    @Test
    public void whenTemplateWithoutName() {
        var generate = new Generate();
        var temple = "I am a, Who are ${subject}? ";
        Map<String, String> map = Map.of("name", "Petr",
                "subject", "Arsentev");
        assertThrows(IllegalArgumentException.class, () -> generate.produce(temple, map));
    }

    @Test
    public void whenTemplateWithoutSubject() {
        var generate = new Generate();
        var temple = "I am a ${name}, Who are ? ";
        Map<String, String> map = Map.of("name", "Petr",
                "subject", "Arsentev");
        assertThrows(IllegalArgumentException.class, () -> generate.produce(temple, map));
    }

    @Test
    public void whenInvalidMap() {
        var generate = new Generate();
        var temple = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("subject", "Arsentev");
        assertThrows(IllegalArgumentException.class, () -> generate.produce(temple, map));
    }

    @Test
    public void whenInvalidKeys() {
        var generate = new Generate();
        var temple = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("name", "Petr",
                "subject", "Arsentev",
                "surname", "Arsentev");
        assertThrows(IllegalArgumentException.class, () -> generate.produce(temple, map));
    }
}
