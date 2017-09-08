package lesson5.labs.prob3;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		IArea circle = new Circle(1.0D);
		IArea rectangle = new Rectangle(2.0D, 3.0D);
		IArea triangle = new Triangle(1.0D, 2.0D);

		List<IArea> list = Arrays.asList(circle, rectangle, triangle);

		double sumArea = 0D;
		for (IArea a : list) {
			sumArea += a.computeArea();
		}
		System.out.print("total area is " + sumArea);
	}

}
