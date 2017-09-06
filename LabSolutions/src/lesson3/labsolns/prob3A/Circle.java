package lesson3.labsolns.prob3A;

public class Circle extends Cylinder {

	Circle(double radius) {
		super(radius, 0D);
	}

	public double computerArea() {
		return Math.PI * radius * radius;
	}
}
