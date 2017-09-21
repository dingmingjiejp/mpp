package lesson9.labs.prob14;

import java.util.Optional;

public class MySingletonLazy {
	private static MySingletonLazy instance=null;

	private MySingletonLazy(){}

	private static MySingletonLazy create()
	{
		System.out.println("create");
		instance=new MySingletonLazy();
		return instance;
	}
	public static MySingletonLazy getInstance()
	{
		return Optional.ofNullable(instance).orElseGet(()->create());
	}

	public static void main(String[] args) {

		MySingletonLazy l1=MySingletonLazy.getInstance();
		MySingletonLazy l2=MySingletonLazy.getInstance();
		System.out.println(l1+", "+l2);
	}

}
