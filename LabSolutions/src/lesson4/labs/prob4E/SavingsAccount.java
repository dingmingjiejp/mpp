package lesson4.labs.prob4E;

public class SavingsAccount extends Account {

	private double balance;
	private double interestRate;
	private String accId;

	public SavingsAccount(String accId, double interestRate, double startBalance) {
		this.accId = accId;
		this.interestRate = interestRate;
		this.balance = startBalance;
	}


	@Override
	String getAccountID() {
		return accId;
	}

	@Override
	double getBalance() {
		return balance;
	}

	@Override
	double computeUpdateBalance() {
		return balance + (interestRate * balance);
	}

}
