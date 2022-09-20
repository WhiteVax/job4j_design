package ru.job4j.design.ocp;

import org.junit.Test;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.StoreEmployee;

import java.util.GregorianCalendar;
import java.util.Objects;

import static org.assertj.core.api.Assertions.*;

public class StoreReportJsonTest {

    @Test
    public void whenCorrectTemplateRepJson() {
        var store = new StoreEmployee();
        var calendar = new GregorianCalendar(2022, 10, 12, 13, 50);
        var worker = new Employee("Pavel", calendar, calendar, 30_000);
        store.add(worker);
        store.add(new Employee("Ivan", calendar, calendar, 35_000));
        var report = new ReportJson(store);
        var expected = new StringBuilder();
        expected.append("[{\"name\":\"Pavel\",\"hired\":")
                .append("{\"year\":2022,\"month\":10,\"dayOfMonth\":12,")
                .append("\"hourOfDay\":13,\"minute\":50,\"second\":0},")
                .append("\"fired\":{\"year\":2022,\"month\":10,")
                .append("\"dayOfMonth\":12,\"hourOfDay\":13,\"minute\":50,")
                .append("\"second\":0},\"salary\":30000.0},{\"name\":\"Ivan\",")
                .append("\"hired\":{\"year\":2022,\"month\":10,\"dayOfMonth\":12,")
                .append("\"hourOfDay\":13,\"minute\":50,\"second\":0},")
                .append("\"fired\":{\"year\":2022,\"month\":10,\"dayOfMonth\":12,")
                .append("\"hourOfDay\":13,\"minute\":50,\"second\":0},")
                .append("\"salary\":35000.0}]");
        assertThat(report.generate(Objects::nonNull))
                .isEqualTo(expected.toString());
    }
}
