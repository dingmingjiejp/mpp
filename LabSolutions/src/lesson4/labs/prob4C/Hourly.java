package lesson4.labs.prob4C;

public class Hourly extends Employee{

	protected double hourlyWage;
	protected int hoursPerWeek;
	
	public Hourly(int empId,double hourWage, int hours)
	{
		super(empId);
		hourlyWage=hourWage;
		hoursPerWeek=hours;
	}
	@Override
	public double calcGrossPay(int month, int year) {
		return hoursPerWeek*hourlyWage;
	}

}
