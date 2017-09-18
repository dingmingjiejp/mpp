package lesson7.labs.prob4.fly;

public interface CannotFly extends FlyBehavior{

	default void fly() {
		System.out.println("\tcannot fly");	
	}

}
