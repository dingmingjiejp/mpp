package lesson5.labs.prob2;

import lesson5.labs.prob2.fly.CannotFly;
import lesson5.labs.prob2.quack.MuteQuack;

public class DecoyDuck extends Duck{

	public DecoyDuck()
	{
		flyBehavior=new CannotFly();
		quackBehavior=new MuteQuack();
	}
	
	@Override
	public void display() {
		System.out.println("\tdisplaying");
	}
	
}
