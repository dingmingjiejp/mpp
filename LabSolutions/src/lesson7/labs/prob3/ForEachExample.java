package lesson7.labs.prob3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Consumer;


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
		MyConsumer consumer = new MyConsumer();
		list.forEach(consumer);			
	}
	
	//implement a Consumer
	class MyConsumer implements Consumer<String>
	{
		@Override
		public void accept(String str) {
			System.out.println(str.toUpperCase());
		}
		
	}
	
	
}