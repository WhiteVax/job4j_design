package ru.job4j.design.ocp;

import org.junit.Test;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.StoreEmployee;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

public class StoreReportXmlTest {
    @Test
    public void whenCorrectTemplateRepXml() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        var store = new StoreEmployee();
        var calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JUNE, 13);
        var worker = new Employee("Pavel", calendar, calendar, 30_000);
        store.add(worker);
        store.add(new Employee("Ivan", calendar, calendar, 35_000));
        var report = new ReportXml(store);
        var expected = new StringBuilder();
        expected.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("\n<employees>\n")
                .append("    <list>\n")
                .append("        <fired>")
                .append(formatter.format(worker.getFired().getTime()))
                .append("</fired>\n")
                .append("        <hired>")
                .append(formatter.format(worker.getHired().getTime()))
                .append("</hired>\n")
                .append("        <name>").append(worker.getName())
                .append("</name>\n")
                .append("        <salary>").append(worker.getSalary())
                .append("</salary>\n")
                .append("    </list>\n")
                .append("</employees>\n");
        assertThat(report.generate(employee -> employee.getSalary() < 33_000))
                .isEqualTo(expected.toString());
    }
}
