package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithCommentAndWithHoldLine() {
        var path = "./data/app.properties";
        var config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hib"), is(Matchers.nullValue()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPaisWithViolationTemple() {
        var path = "./data/with_violation_of_temple.properties";
        var config = new Config(path);
        config.load();
    }

    @Test
    public void whenPluralEquals() {
        var path = "./data/with_plural_equals.properties";
        var config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres=name"));
        assertThat(config.value("# PostgresSQL hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }
}
