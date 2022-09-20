package ru.job4j.design.ocp;

import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Report;
import ru.job4j.design.srp.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class ReportXml implements Report {

    JAXBContext context;
    Marshaller marshaller;

    private Store store;

    public ReportXml(Store store) {
        this.store = store;
        try {
            this.context = JAXBContext.newInstance(EmployeeList.class);
            this.marshaller = context.createMarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var xml = "";
        try {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (var writer = new StringWriter()) {
                marshaller.marshal(new EmployeeList(store.findBy(filter)), writer);
                xml = writer.getBuffer().toString();
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @XmlRootElement(name = "employees")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class EmployeeList {
        @XmlElement(name = "list")
        List<Employee> listEmployee;

        public EmployeeList() {
        }

        public EmployeeList(List<Employee> employee) {
            this.listEmployee = employee;
        }
    }
}
