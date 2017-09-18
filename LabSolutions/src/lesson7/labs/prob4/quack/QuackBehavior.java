package lesson7.labs.prob4.quack;

public interface QuackBehavior {
	
	default void quack()	 
	{
		System.out.println("\tquacking");	
	}
}
