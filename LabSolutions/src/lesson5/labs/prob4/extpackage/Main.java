package lesson5.labs.prob4.extpackage;

import java.time.LocalDate;

import lesson5.labs.prob4.CustOrderFactory;
import lesson5.labs.prob4.Customer;
import lesson5.labs.prob4.Order;

public class Main {
	public static void main(String[] args) {
		Customer cust = CustOrderFactory.newCustomer("Bob");

		Order order = CustOrderFactory.newOrder(cust, LocalDate.now());
		order.addItem("Shirt");
		order.addItem("Laptop");

		order = CustOrderFactory.newOrder(cust, LocalDate.now());
		order.addItem("Pants");
		order.addItem("Knife set");

		System.out.println(cust.getOrders());
	}
}


