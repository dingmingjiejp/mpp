package lesson4.labs.prob4E;

import java.util.ArrayList;
import java.util.List;

public class Employee {

	private String name;

	private List<Account> accountList;

	public Employee(String name) {
		this.name = name;
		accountList = new ArrayList<Account>();
	}

	public double computeUpdatedBalanceSum() {
		double updDalanceSum = 0.0;
		for (Account acc : accountList) {
			updDalanceSum += acc.computeUpdateBalance();
		}
		return updDalanceSum;
	}

	public void addAccount(Account acc) {
		accountList.add(acc);
	}

	public String getName() {
		return name;
	}
}
