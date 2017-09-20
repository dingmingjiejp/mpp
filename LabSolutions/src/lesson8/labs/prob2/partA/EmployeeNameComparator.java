package lesson8.labs.prob2.partA;

import java.util.Comparator;

/* A functor, but not a closure */
public class EmployeeNameComparator implements Comparator<Employee> {
	@Override
	public int compare(Employee e1, Employee e2) {
		if(e1.name.compareTo(e2.name) == 0) {
			if(e1.equals(e2)) return 0;
			else return -1;
		}
		return e1.name.compareTo(e2.name);
	}
}
