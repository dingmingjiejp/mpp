package lesson4.labs.prob4E;

import java.util.List;

public class Admin {
	public static double computeUpdatedBalanceSum(List<Employee> list) {
		double updBalanceSum = 0.0;
		for (Employee e : list) {
			updBalanceSum += e.computeUpdatedBalanceSum();
		}
		return updBalanceSum;
	}
}
