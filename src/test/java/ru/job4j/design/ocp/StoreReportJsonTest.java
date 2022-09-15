package ru.job4j.design.ocp;

import org.junit.Test;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.StoreEmployee;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.design.ocp.ReportJson.DATE_FORMAT;

public class StoreReportJsonTest {

    @Test
    public void whenCorrectTemplateRepJson() {
        var store = new StoreEmployee();
        var date = Calendar.getInstance();
        var worker = new Employee("Pavel", date, date, 30_000);
        store.add(worker);
        store.add(new Employee("Ivan", date, date, 35_000));
        var report = new ReportJson(store);
        var expected = new StringBuilder();
        expected.append("{\"name\":\"").append(worker.getName())
                .append("\",\"date fired\":\"")
                .append(DATE_FORMAT.format(worker.getFired().getTime()))
                .append("\",\"date hired\":\"")
                .append(DATE_FORMAT.format(worker.getFired().getTime()))
                .append("\",\"salary\":")
                .append(Math.round(worker.getSalary())).append("}")
                .append(System.lineSeparator());
        assertThat(report.generate(employee -> employee.getSalary() < 33_000))
                .isEqualTo(expected.toString());
    }
}
