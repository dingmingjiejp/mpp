package lesson5.labs.prob4;

import java.time.LocalDate;

public class CustOrderFactory {

	public static Order newOrder(Customer cust, LocalDate date) {
		if(cust == null) throw new NullPointerException("Null customer");
		Order ord = new Order(date);
		cust.addOrder(ord);
		return ord;
	}

	public static Customer newCustomer(String name) {
		return new Customer(name);
	}

	public static Item newItem(String name) {
		return new Item(name);
	}

}
