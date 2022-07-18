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

        byte count = 5;
        short counter = 10000;
        int cities = 18;
        long distance = 10_000_000L;
        float percent = 0.76F;
        double miles = 13.3;
        char typeVehicle = 'C';
        boolean road = true;
        LOG.debug("Vehicle info count : {}, counter : {}, cities : {}, distance : {}, percent : {}, miles : {}, typeVehicle : {}, road : {}.",
        count, counter, cities, distance, percent, miles, typeVehicle, road);
    }
}
