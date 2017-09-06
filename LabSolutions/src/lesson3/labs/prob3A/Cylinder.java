package lesson3.labs.prob3A;

public class Cylinder {
	protected double radius;
	public double height;

	Cylinder(double redius, double height) {
		this.radius = redius;
		this.height = height;
	}

	public double computeVolume() {
		return Math.PI * radius * radius * height;
	}

}
