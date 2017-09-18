package lesson7.labs.prob4.quack;

public interface Squeak extends QuackBehavior{

	default void quack() {
		System.out.println("\tsqueaking");	
	}

}
