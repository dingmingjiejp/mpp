package lesson10.labs.prob5.soln1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import lesson10.labs.prob5.Employee;
import lesson10.labs.prob5.Main;

public class TestAsString {
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

	@Test
	public void test() {
		String str = Main.asStringList(emps);
		assertThat(str, is("Alice Richards, Joe Stevens, John Sims, Steven Walters"));
	}

}
