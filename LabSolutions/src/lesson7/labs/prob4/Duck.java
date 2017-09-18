package lesson7.labs.prob4;

import lesson7.labs.prob4.fly.FlyBehavior;
import lesson7.labs.prob4.quack.QuackBehavior;

public abstract class Duck implements FlyBehavior, QuackBehavior{

	public abstract void display();
	
	public void swim()
	{
		System.out.println("\tswimming");
	}

	
}
