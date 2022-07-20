package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;

public class ConverterXML {

    public static void main(String[] args) throws Exception {

        var person = new Person("Name", "Surname", 18, true, new Contact(5345, "7959, 5462, 234"),
                new char[]{'B', 'C'});

        var context = JAXBContext.newInstance(Person.class);
        var marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (var writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        /* Для десериализации нам нужно создать десериализатор */
        var unmarshaller = context.createUnmarshaller();
        try (var reader = new StringReader(xml)) {
            var result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

        try (var reader = new BufferedReader(new FileReader("./data/person.xml"))) {
            var result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}

