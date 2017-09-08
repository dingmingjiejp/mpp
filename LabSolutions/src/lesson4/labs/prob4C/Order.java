package lesson4.labs.prob4C;

import java.util.Date;

public class Order {

	public String orderNo;
	public Date orderDate;
	public double orderAmount;
	
	public Commissioned commissioned;
	
	public Order(Commissioned comm, String no, Date date, double amount)
	{
		commissioned=comm;
		orderNo=no;
		orderDate=date;
		orderAmount=amount;
	}
}
