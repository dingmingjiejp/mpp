package lesson7.labs.prob4.quack;

public interface MuteQuack extends QuackBehavior{

	default void quack() {
		System.out.println("\tcannot quack");	
	}

}
