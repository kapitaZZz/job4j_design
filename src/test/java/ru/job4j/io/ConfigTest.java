package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String correctProps = "./app.properties";
        Config config = new Config(correctProps);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
    }

    @Test
    public void whenPairWithComment() {
        String commentProps = "./comment.properties";
        Config config = new Config(commentProps);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoPair() {
        String withNullProps = "./key_off.properties";
        Config config = new Config(withNullProps);
        config.load();
        assertThat(config.value("hibernate.connection.driver_class"), is(nullValue()));
    }

    @Test
    public void whenManyEquals() {
        String commentProps = "./comment.properties";
        Config config = new Config(commentProps);
        config.load();
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql:=//127.0.0.1:5432=/trackstudio"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoEquals() {
        String withNullProps = "./key_off.properties";
        Config config = new Config(withNullProps);
        config.load();
        assertThat(config.value("hibernate.connection.password"), is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoKey() {
        String withNullProps = "./key_off.properties";
        Config config = new Config(withNullProps);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is(nullValue()));
    }
}