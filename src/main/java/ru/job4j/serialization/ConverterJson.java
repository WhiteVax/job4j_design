package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConverterJson {
    public static void main(String[] args) {
        final var person = new Person("Name", "Surname", 18, true, new Contact(5345, "7959, 5462, 234"),
                new char[]{'B', 'C'});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        var fromJson = gson.fromJson(gson.toJson(person), Person.class);
        System.out.println(fromJson);
        System.out.println(fromJson.getContact());
    }
}
