package lesson9.labs.prob12;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		List<Employee> list = Arrays.asList(new Employee("Joe", "Davis", 120000),
				          new Employee("John", "Sims", 110000),
				          new Employee("Joe", "Stevens", 200000),
		                  new Employee("Andrew", "Reardon", 80000),
		                  new Employee("Joe", "Cummings", 760000),
		                  new Employee("Steven", "Walters", 135000),
		                  new Employee("Thomas", "Blake", 111000),
		                  new Employee("Alice", "Richards", 101000),
		                  new Employee("Donald", "Trump", 100000));

		//print the number of Employees in list whose salary > 100000 and whose last name begins
		//with a letter that comes after the letter 'E'
		System.out.println(
				"the number of Employees whose salary > 100000 and last name after E : " +
						LambdaLibrary.EMPLOYEELIST_WITH_MINSALARY_STARTLETTER.apply(list, 100000D, "E").size());
		System.out.println("");


		//print a list of sorted full names - all upper case -- of Employees with
		//salary > 85000 and whose first name begins with a letter that comes before the letter 'R'
		System.out.println("full names list of salary > 85000 and first name before the letter'R'");
		System.out.println("------------------------------------------------------------");
		LambdaLibrary.EMPLOYEENAMELIST_WITH_MINSALARY_STARTLETTER2.apply(list, 85000D, "R").forEach(System.out::println);;

	}

}
