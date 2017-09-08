package lesson4.labs.prob4C;

public abstract class Employee {
	protected int empId;
	
	public Employee(int emp)
	{
		empId=emp;
	}
	public void print()
	{
		
	}
	
	public Paycheck calcCompensation(int month, int year)
	{
		double gross=calcGrossPay(month, year);
		return new Paycheck(gross);
		
	}
	
	public abstract double calcGrossPay(int month, int year);
	
}
