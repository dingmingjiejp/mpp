package lesson4.labs.prob4C;

import java.util.Date;

public class Main {

	public static void main(String[] args) {

		Employee hour=new Hourly(1,10,20);		
		System.out.println("hourly salary="+hour.calcCompensation(12, 2017).getNetPay());
		
		Employee salaryied=new Salaried(2,4000);		
		System.out.println("salaryied salary="+salaryied.calcCompensation(12, 2017).getNetPay());
		
		Commissioned commission=new Commissioned(3,0.2,1000);
		Order order1=new Order(commission,"1",new Date(2017,7,1),1000);		
		Order order2=new Order(commission,"2",new Date(2017,7,2),1000);
		Order order3=new Order(commission,"3",new Date(2017,9,1),1000);
		commission.orderList.add(order1);
		commission.orderList.add(order2);
		commission.orderList.add(order3);
		
		System.out.println("commission salary="+commission.calcCompensation(8, 2017).getNetPay());
	}
}
