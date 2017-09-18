package lesson7.labs.prob4.fly;

public interface FlyBehavior {
	default void fly()
	{
		System.out.println("\tfly with wings");	
	}
}
