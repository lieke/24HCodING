package nl.ing.cla.model;

import nl.ing.cla.exception.BalanceToLowToTransferMoneyException;

public abstract class Account {
	double balance;
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void transferTo(double price, Account otherAccount) throws BalanceToLowToTransferMoneyException {
		if (doIHaveEnoughFundsToTransfer(price)) {
			this.balance = this.balance - price;
			otherAccount.setBalance(otherAccount.getBalance() + price);
		} else throw new BalanceToLowToTransferMoneyException(this, price);
	}
	
	private boolean doIHaveEnoughFundsToTransfer(double price) {
		return !(price > this.balance);
	}
}
