package lesson4.labs.prob4C;

public class Salaried extends Employee{

	protected double salary;
	public Salaried(int emp, double salary) {
		super(emp);
		this.salary=salary;
	}

	@Override
	public double calcGrossPay(int month, int year) {
		// TODO Auto-generated method stub
		return salary;
	}

}
