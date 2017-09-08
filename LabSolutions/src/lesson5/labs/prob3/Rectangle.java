package lesson5.labs.prob3;

public class Rectangle implements IArea{
	private final double width;
	private final double length;

	public Rectangle(double width, double length) {
		this.width = width;
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public double getLength() {
		return length;
	}

	@Override
	public double computeArea() {
		return width * length;
	}
}
