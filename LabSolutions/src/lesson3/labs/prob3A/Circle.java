package lesson3.labs.prob3A;

public class Circle extends Cylinder {

	Circle(double radius) {
		super(radius, 0D);
	}

	public double computerArea() {
		return Math.PI * radius * radius;
	}
}
