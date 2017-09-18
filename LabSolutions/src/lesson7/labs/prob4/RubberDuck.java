package lesson7.labs.prob4;

import lesson7.labs.prob4.fly.CannotFly;
import lesson7.labs.prob4.quack.Squeak;

public class RubberDuck extends Duck implements CannotFly, Squeak{

	public RubberDuck()
	{
	}
	
	@Override
	public void display() {
		System.out.println("\tdisplaying");		
	}

}
