package lesson8.labs.prob2.partC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeInfo {
	static enum SortMethod {BYNAME, BYSALARY};
	
	
	public void sort(List<Employee> emps, SortMethod method) {
		Comparator<Employee> c = (e1, e2) -> {
			if(e1.equals(e2)) return 0;
			
			if(method == SortMethod.BYNAME) {
				if(e1.name.compareTo(e2.name) == 0) {
					return -1;
				} else {
					return e1.name.compareTo(e2.name);
				}
			} else {
				if(e1.salary < e2.salary) return -1;
				else return 1;
			}
		};
		Collections.sort(emps, c);
	}
//	public void sort(List<Employee> emps, final SortMethod method) {
//		class EmployeeComparator implements Comparator<Employee> {
//			@Override
//			public int compare(Employee e1, Employee e2) {
//				if(method == SortMethod.BYNAME) {
//					if(e1.name.compareTo(e2.name) == 0) {
//						if(e1.equals(e2)) return 0;
//						else return -1;
//					} else {
//						return e1.name.compareTo(e2.name);
//					}
//				} else {
//					if(e1.salary == e2.salary) return 0;
//					else if(e1.salary < e2.salary) return -1;
//					else return 1;
//				}
//			}
//		}
//		Collections.sort(emps, new EmployeeComparator());
//	}
	
	public static void main(String[] args) {
		List<Employee> emps = new ArrayList<>();
		emps.add(new Employee("Joe", 100000));
		emps.add(new Employee("Tim", 50000));
		emps.add(new Employee("Andy", 60000));
		EmployeeInfo ei = new EmployeeInfo();
		ei.sort(emps, EmployeeInfo.SortMethod.BYNAME);
		System.out.println(emps);
		//same instance
		ei.sort(emps, EmployeeInfo.SortMethod.BYSALARY);
		System.out.println(emps);
		
	}
}
