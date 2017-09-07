package lesson3.labs.prob4;

public class Condo extends Building {

	private int numberOfFloors;

	public Condo(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	public double computeRent(){
		return 500 * numberOfFloors;
	}
}
