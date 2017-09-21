package lesson9.labs.prob12;

import java.util.List;
import java.util.stream.Collectors;

public class LambdaLibrary {

	public static final TriFunction<List<Employee>, Double, String, List<Employee>> EMPLOYEELIST_WITH_MINSALARY_STARTLETTER =
			(emps, minSalary, beginLetter) ->
				emps.stream().filter(r -> r.getSalary() > minSalary &&
					  	r.getLastName().substring(0, 1).compareTo(beginLetter)>0)
			  			.collect(Collectors.toList());


	public static final TriFunction<List<Employee>, Double, String, List<String>> EMPLOYEENAMELIST_WITH_MINSALARY_STARTLETTER2 =
			(emps, minSalary, beginLetter) ->
				emps.stream().filter(r -> r.getSalary() > minSalary &&
					  	r.getFirstName().substring(0, 1).compareTo(beginLetter)<0)
			  			.map(r -> r.getFirstName().toUpperCase() + " " + r.getLastName().toUpperCase())
			  			.sorted()
			  			.collect(Collectors.toList());

}
