package lesson8.labs.prob6;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Employee {
	String name;
	int salary;
	public Employee(String n, int s) {
		this.name = n;
		this.salary = s;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("<");
		sb.append("name: ");
		sb.append(name);
		sb.append(" salary: ");
		sb.append("" + salary+">");
		return sb.toString();
		
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String n)
	{
		name=n;
	}

	public static void main(String[] args)
	{
		evaluator();
		
	}
	
	
	// part 1 & part 2 
	public static void evaluator() {
		Employee emp=new Employee("John",1000);
		
//		A. (Employee e) -> e.getName()
// 		Method reference type: Class::instanceMethod
		Function<Employee,String> getName = Employee::getName;
		System.out.println("getName = "+ getName.apply(emp));

//  	B. (Employee e,String s) -> e.setName(s)
//  	Method reference type: Class::instanceMethod
		BiConsumer<Employee,String> setName = Employee::setName;
		setName.accept(emp,"Tom");
		System.out.println("getName = "+ getName.apply(emp));
		
//		C. (String s1,String s2) -> s1.compareTo(s2)
//  	Method reference type: Class::instanceMethod		
		BiFunction<String,String, Integer> compareTo = String::compareTo;
		System.out.println("compareTo abc==bcd = "+ compareTo.apply("abc","bcd"));
		
//		D. (Integer x,Integer y) -> Math.pow(x,y)
//  	Method reference type: Class::staticMethod.	
		BiFunction<Integer, Integer, Double> pow = Math::pow;
		System.out.println("pow(2,3) = "+ pow.apply(2,3));
			
//		E. (Apple a) -> a.getWeight()
//  	Method reference type: Class::instanceMethod
		Apple apple=new Apple(100);
		Function<Apple,Integer> getWeight = Apple::getWeight;
		System.out.println("getWeight = "+ getWeight.apply(apple));
		
//		F. (String x) -> Integer.parseInt(x);
//  	Method reference type: Class::staticMethod
		Function<String, Integer> parseInt = Integer::parseInt;
		System.out.println("parseInt(1001) = "+ parseInt.apply("1001"));
		
		
//		G. EmployeeNameComparator comp = new EmployeeNameComparator(); 
//		(Employee e1, Employee e2) -> comp.compare(e1,e2)
//  	Method reference type: object::instanceMethod
		Employee emp1=new Employee("John",1000);
		EmployeeNameComparator comp = new EmployeeNameComparator(); 
		BiFunction<Employee,Employee,Integer> compare = comp::compare;
		System.out.println("compare = "+ compare.apply(emp,emp1));
	}
}

