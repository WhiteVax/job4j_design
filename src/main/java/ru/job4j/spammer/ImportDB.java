package ru.job4j.spammer;

import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private final Properties properties;
    private final String dump;

    public ImportDB(Properties cfg, String dump) {
        this.properties = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (var reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(e -> {
                String[] s = e.split(";");
                if (s.length != 2) {
                    throw new IllegalArgumentException(String.format("Wrong line %s.", Arrays.toString(s)));
                }
                users.add(new User(s[0], s[1]));
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("jdbc.driver"));
        try (var connection = DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password")
        )) {
            for (var user : users) {
                try (PreparedStatement ps = connection.prepareStatement("INSERT INTO users(name, email) VALUES (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        var properties = new Properties();
        try (var input = ImportDB.class.getClassLoader().getResourceAsStream("dump.properties")) {
            properties.load(input);
        }
        var db = new ImportDB(properties, "data/dump.txt");
        db.save(db.load());
    }
}
