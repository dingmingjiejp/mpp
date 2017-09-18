package lesson7.labs.prob4;

import lesson7.labs.prob4.fly.CannotFly;
import lesson7.labs.prob4.quack.MuteQuack;

public class DecoyDuck extends Duck implements CannotFly,MuteQuack{

	public DecoyDuck()
	{
	}
	
	@Override
	public void display() {
		System.out.println("\tdisplaying");
	}
	
}
