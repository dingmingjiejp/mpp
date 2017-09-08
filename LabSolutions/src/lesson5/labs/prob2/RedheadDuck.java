package lesson5.labs.prob2;

import lesson5.labs.prob2.fly.FlyWithWings;
import lesson5.labs.prob2.quack.Quack;

public class RedheadDuck extends Duck{

	public RedheadDuck()
	{
		flyBehavior=new FlyWithWings();
		quackBehavior=new Quack();
	}
	
	@Override
	public void display() {
		System.out.println("\tdisplaying");
	}

}
