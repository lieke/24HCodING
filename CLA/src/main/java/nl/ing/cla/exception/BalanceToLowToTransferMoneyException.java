package nl.ing.cla.exception;

import nl.ing.cla.model.Account;

public class BalanceToLowToTransferMoneyException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Account account;
	public double price;

	public BalanceToLowToTransferMoneyException(Account account, double price) {
		// TODO Auto-generated constructor stub
	}

}
