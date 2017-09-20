package lesson8.labs.prob5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;


public class ForEachExample {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<String> list = Arrays.asList("Hello there", "Goodbye", "Back soon", 
				"Away", "On Vacation", "Everywhere you want to be");
		
		//print each element of the list in upper case format
		new ForEachExample().print(list);
	}
	
	public void print(List<String> list)
	{
		Function<String,String> toUpperCase = String::toUpperCase;
		
		list.forEach(str->System.out.println(toUpperCase.apply(str)));	
		
	}
	

	
	
}