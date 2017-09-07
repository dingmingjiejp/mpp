package lesson4.labs.prob4E;

public class CheckingAccount extends Account {

	private double balance;
	private double monthlyFee;
	private String accId;

	public CheckingAccount(String accId, double monthlyFee, double startBalance) {
		this.accId = accId;
		this.monthlyFee = monthlyFee;
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
		return balance - monthlyFee;
	}

}
