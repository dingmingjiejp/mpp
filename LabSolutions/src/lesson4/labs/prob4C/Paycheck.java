package lesson4.labs.prob4C;

public class Paycheck {
	double grossPay;
	final double fica=.23;
	final double state=.05;
	final double local=.01;
	final double medicare=.03;
	final double socialSecurity=.075;

	public Paycheck(double grossPay)
	{
		this.grossPay=grossPay;
		
	}
	
	public void print()
	{
		
	}
	public double getNetPay()
	{
		return (1-fica-state-local-medicare-socialSecurity)*grossPay;
	}
}
