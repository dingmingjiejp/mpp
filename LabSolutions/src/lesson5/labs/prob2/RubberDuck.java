package lesson5.labs.prob2;

import lesson5.labs.prob2.fly.CannotFly;
import lesson5.labs.prob2.quack.Squeak;

public class RubberDuck extends Duck{

	public RubberDuck()
	{
		flyBehavior=new CannotFly();
		quackBehavior=new Squeak();
	}
	
	@Override
	public void display() {
		System.out.println("\tdisplaying");		
	}

}
