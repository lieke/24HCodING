package nl.ing.cla.model;

public class ParentAccount {

	int id;
	String accountNumber;
	double balance;
	
	public ParentAccount(int id, String accountNumber, double balance){
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.id = id;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	
}
