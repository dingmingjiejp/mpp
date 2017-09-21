package lesson9.labs.prob11b;

import java.util.List;
import java.util.stream.Collectors;

public class LambdaLibrary {

	public static final TriFunction<List<Employee>, Double, String, String> NAMES_WITH_MINSALARY_BEGINLETTER =
			(emps, minSalary, beginLetter) ->
				emps.stream().filter(r -> r.getSalary() > minSalary &&
					  	r.getLastName().substring(0, 1).compareTo(beginLetter)>0)
			  			.map(r -> r.firstName + " " + r.lastName)
			  			.sorted()
			  			.collect(Collectors.joining(","));
}
