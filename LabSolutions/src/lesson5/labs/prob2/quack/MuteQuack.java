package lesson5.labs.prob2.quack;

public class MuteQuack implements QuackBehavior{

	@Override
	public void quack() {
		System.out.println("\tcannot quack");	
	}

}
