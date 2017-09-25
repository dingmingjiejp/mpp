package lesson10.labs.prob5.soln2;

import lesson10.labs.prob5.Employee;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestMain {
    private List<Employee> emps;


    @Before
    public void before() {
        emps = Arrays.asList(new Employee("Joe", "Davis", 120000),
                new Employee("John", "Sims", 110000),
                new Employee("Joe", "Stevens", 200000),
                new Employee("Andrew", "Reardon", 80000),
                new Employee("Joe", "Cummings", 760000),
                new Employee("Steven", "Walters", 135000),
                new Employee("Thomas", "Blake", 111000),
                new Employee("Alice", "Richards", 101000),
                new Employee("Donald", "Trump", 100000));
    }

    @org.junit.Test
    public void testLastNameAfterM() {

        assertEquals(false,Main.lastNameAfterM(new Employee("Joe", "Cummings", 760000)));
        assertEquals(true,Main.lastNameAfterM(new Employee("Andrew", "Reardon", 80000)));

    }

    @org.junit.Test
    public void testSalaryGreaterThan100000() {
        assertEquals(false,Main.salaryGreaterThan100000(new Employee("Andrew", "Reardon", 80000)));
        assertEquals(true,Main.salaryGreaterThan100000(new Employee("John", "Sims", 110000)));

    }

    @org.junit.Test
    public void testAsList() {
        String str = Main.asStringList(emps);
        // at least one Employee is excluded
        assert str.indexOf("Donald Trump") < 0;
        // at least one is included in the list
        assert str.indexOf("Joe Stevens") > 0;

        assertEquals(str,"Alice Richards, Joe Stevens, John Sims, Steven Walters");

    }


}
