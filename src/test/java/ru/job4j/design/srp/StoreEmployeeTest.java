package ru.job4j.design.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Objects;

import static org.assertj.core.api.Assertions.*;
import static  ru.job4j.design.srp.ReportEngine.DATE_FORMAT;

public class StoreEmployeeTest {

    @Test
    public void whenCorrectTemplateRepEngine() {
        var store = new StoreEmployee();
        var date = Calendar.getInstance();
        var worker = new Employee("Pavel", date, date, 30_000);
        store.add(worker);
        store.add(new Employee("Ivan", date, date, 35_000));
        var reportEngine = new ReportEngine(store);
        var expected = new StringBuilder();
        expected.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator()).append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";").append(System.lineSeparator());
        assertThat(reportEngine.generate(employee -> employee.getSalary() < 33_000))
                .isEqualTo(expected.toString());
    }

    @Test
    public void whenCorrectTemplateRepAccounting() {
        var store = new StoreEmployee();
        var date = Calendar.getInstance();
        var worker = new Employee("Pavel", date, date, 30_000);
        store.add(worker);
        var reportEngine = new ReportAccounting(store);
        var expected = new StringBuilder();
        expected.append("Name; Hired; Fired; Salary in dollars;")
                .append(System.lineSeparator()).append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary() / 65).append(";").append(System.lineSeparator());
        assertThat(reportEngine.generate(employee -> employee.getSalary() < 33_000))
                .isEqualTo(expected.toString());
    }

    @Test
    public void whenCorrectTemplateRepHR() {
        var store = new StoreEmployee();
        var date = Calendar.getInstance();
        var second = new Employee("Pavel", date, date, 30_000);
        var first = new Employee("Pavel", date, date, 35_000);
        store.add(first);
        store.add(second);
        var reportEngine = new ReportHR(store);
        var expected = new StringBuilder();
        expected.append("Name; Salary;")
                .append(System.lineSeparator()).append(first.getName()).append(";")
                .append(first.getSalary()).append(";").append(System.lineSeparator())
                .append(second.getName()).append(";").append(second.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(reportEngine.generate(Objects::nonNull))
                .isEqualTo(expected.toString());
    }

    @Test
    public void whenCorrectTemplateRepDev() {
        var store = new StoreEmployee();
        var date = Calendar.getInstance();
        var worker = new Employee("Pavel", date, date, 35_000);
        store.add(worker);
        var reportEngine = new ReportDev(store);
        var expected = new StringBuilder();
        expected.append("<!DOCTYPE html>")
                .append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<title>HTML5</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary;").append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>");
        assertThat(reportEngine.generate(Objects::nonNull))
                .isEqualTo(expected.toString());
    }
}
