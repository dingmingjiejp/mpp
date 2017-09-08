package lesson4.labs.prob4C;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Commissioned extends Employee{

	protected double commission;
	protected double baseSalary;
	public Commissioned(int emp, double commission, double baseSalary ) {
		super(emp);
		this.commission=commission;
		this.baseSalary=baseSalary;
	}

	public List<Order> orderList=new ArrayList<Order>();
	
	
	@Override
	public double calcGrossPay(int month, int year) {
		// TODO Auto-generated method stub
		double sum=0;
		month--;
		for (Order order:orderList)
		{
			Date date=order.orderDate;
			if (date.getYear()==year && date.getMonth()==month)
				sum+=order.orderAmount;
		}
		return baseSalary+commission*sum;
	}

}
