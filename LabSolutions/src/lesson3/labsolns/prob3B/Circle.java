package lesson3.labsolns.prob3B;

public class Circle {
	Cylinder cylinder;

	Circle(double redius) {
		cylinder = new Cylinder(redius, 1D);
	}

	public double computerArea() {
		return cylinder.computeVolume();
	}
}
