package lesson3.labs.prob3A;

public class Main {

	public static void main(String[] args) {

		Cylinder cylinder = new Cylinder(1.0, 2.0);
		System.out.println("cylinder volume is :" + cylinder.computeVolume());

		Circle circle = new Circle(1.0);
		System.out.println("circle area is :" + circle.computerArea());

	}
}
