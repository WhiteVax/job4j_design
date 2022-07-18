package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        var name = "Vlad";
        var surname = "Bedenko";
        int age = 18;
        var education = "University ...";
        var degreeUniversity = "master";
        var city = "Kyiv";
        var birthday = "march";
        var profession = "engineer";
        LOG.debug("User info name : {}, surname : {}, age : {}, education : {}, degree : {}, city : {}, birthday : {},profession : {}.",
        name, surname, age, education, degreeUniversity, city, birthday, profession);
    }
}
