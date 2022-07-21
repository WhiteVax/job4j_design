package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class ConverterJson {
    public static void main(String[] args) {
        final var person = new Person("Name", "Surname", 18, true, new Contact(5345, "7959, 5462, 234"),
                new char[]{'B', 'C'});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        var fromJson = gson.fromJson(gson.toJson(person), Person.class);
        System.out.println(fromJson);
        System.out.println(fromJson.getContact());

        var jsonContact = new JSONObject("{"
                + "\"zipCode\":3126,"
                + "\"phone\":\"+7(759)1235-1235\""
                + "}");
        var contact = new Contact(jsonContact.getInt("zipCode"),
                jsonContact.getString("phone"));
        System.out.println(contact);

        var jsonPerson = new JSONObject();
        jsonPerson.put("name", person.getName());
        jsonPerson.put("surname", person.getSurname());
        jsonPerson.put("age", person.getAge());
        jsonPerson.put("education", person.isEducation());
        jsonPerson.put("contact", person.getContact());
        jsonPerson.put("driverCard", person.getDriverCard());
        System.out.println(jsonPerson);
    }
}
