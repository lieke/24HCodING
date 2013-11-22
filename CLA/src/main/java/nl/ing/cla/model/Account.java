package nl.ing.cla.model;

public abstract class Account {
	double balance;
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void transferTo(double price, Account otherAccount) {
		this.balance = this.balance - price;
		otherAccount.setBalance(otherAccount.getBalance() + price); 
	}
}
