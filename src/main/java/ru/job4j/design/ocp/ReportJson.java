package ru.job4j.design.ocp;

import org.json.JSONObject;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Report;
import ru.job4j.design.srp.Store;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportJson implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private Store store;

    public ReportJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var json = new JSONObject();
        var text = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            json.put("name", employee.getName());
            json.put("date hired", DATE_FORMAT.format(employee.getHired().getTime()));
            json.put("date fired", DATE_FORMAT.format(employee.getFired().getTime()));
            json.put("salary", employee.getSalary());
            text.append(json).append(System.lineSeparator());
        }
        return text.toString();
    }
}
