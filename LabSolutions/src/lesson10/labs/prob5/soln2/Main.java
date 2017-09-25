package lesson10.labs.prob5.soln2;

import lesson10.labs.prob5.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static String asStringList(List<Employee> emps) {
        return emps.stream()
                .filter(Main::salaryGreaterThan100000)
                .filter(Main::lastNameAfterM)
                .map(Main::fullName)
                .sorted()
                .collect(Collectors.joining(", "));
    }

    public static boolean salaryGreaterThan100000(Employee e) {
        return e.getSalary() > 100000;
    }

    public static boolean lastNameAfterM(Employee e) {
        return e.getLastName().charAt(0) > 'M';
    }

    private static String fullName(Employee e) {
        return e.getFirstName() + " " + e.getLastName();
    }

}
