package lesson10.labs.prob5.soln1;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;

import lesson10.labs.prob5.Employee;
import lesson10.labs.prob5.Main;

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
	public void test() {
		String str = Main.asStringList(emps);
		// at least one Employee is excluded
		assert str.indexOf("Donald Trump") < 0;
		// at least one is included in the list
		assert str.indexOf("Joe Stevens") > 0;

		assertEquals(str,"Alice Richards, Joe Stevens, John Sims, Steven Walters");

	}

}
