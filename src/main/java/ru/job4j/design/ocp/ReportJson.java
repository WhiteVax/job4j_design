package ru.job4j.design.ocp;

import com.google.gson.Gson;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Report;
import ru.job4j.design.srp.Store;

import java.util.function.Predicate;

public class ReportJson implements Report {

    private Store store;
    private Gson gson;

    public ReportJson(Store store) {
        this.store = store;
        this.gson = new Gson();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}
