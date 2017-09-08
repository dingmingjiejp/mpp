package lesson5.labs.prob2;

import lesson5.labs.prob2.fly.FlyBehavior;
import lesson5.labs.prob2.quack.QuackBehavior;

public abstract class Duck {

	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	
	public abstract void display();
	
	public void swim()
	{
		System.out.println("\tswimming");
	}
	public void quack()
	{
		if (quackBehavior!=null)
			quackBehavior.quack();
	}
	public void fly()
	{
		if (flyBehavior!=null)
			flyBehavior.fly();
	}
}
